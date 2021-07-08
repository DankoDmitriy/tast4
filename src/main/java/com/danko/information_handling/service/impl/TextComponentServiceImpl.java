package com.danko.information_handling.service.impl;

import com.danko.information_handling.comparator.SentencesCountComparator;
import com.danko.information_handling.entity.TextComponentInformation;
import com.danko.information_handling.exception.TextException;
import com.danko.information_handling.service.TextComponentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.danko.information_handling.entity.ComponentType.*;

public class TextComponentServiceImpl implements TextComponentService {
    private static Logger logger = LogManager.getLogger();
    private static final String VOWEL_REGEX = "[AEIOUaeiou]";
    private static final String CONSONANT_REGEX = "[[^AEIOUaeiou]&&A-Za-z]";
    private static final String PUNCTUATION = "\\p{Punct}";

    @Override
    public int countEqualWords(TextComponentInformation component, String searchWord) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        int count = 0;
        List<TextComponentInformation> paragraphs = component.getChildren();
        for (TextComponentInformation paragraph : paragraphs) {
            List<TextComponentInformation> sentences = paragraph.getChildren();
            for (TextComponentInformation sentence : sentences) {
                List<TextComponentInformation> words = sentence.getChildren();
                for (TextComponentInformation word : words) {
                    List<TextComponentInformation> leafs = word.getChildren();
                    StringBuilder sb = new StringBuilder();
                    String collectedWord = "";
                    for (TextComponentInformation leaf : leafs) {
                        if (leaf.getType() == SYMBOL_LEAF) {
                            sb.append(leaf.toString());
                        }
                    }
                    collectedWord = sb.toString().toLowerCase();
                    if (collectedWord.equals(searchWord.toLowerCase())) {
                        count++;
                    }
                }
            }
        }
        logger.log(Level.INFO, String.format("Has been counted equal words. Search word = '%s' , count = %d", searchWord, count));
        return count;
    }

    public void sortParagraphsBySentences(TextComponentInformation component) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        List<TextComponentInformation> paragraphs = component.getChildren();
        paragraphs.sort(new SentencesCountComparator());
    }

    @Override
    public void removeSentencesWithMinWords(TextComponentInformation component, int minWords) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        int count = 0;
        List<TextComponentInformation> paragraphs = component.getChildren();
        for (TextComponentInformation paragraph : paragraphs) {
            List<TextComponentInformation> sentences = paragraph.getChildren();
            int tmpStartSize = sentences.size();
            sentences.removeIf(s -> s.getChildren().size() < minWords);
            count += tmpStartSize - sentences.size();
        }
        logger.log(Level.INFO, String.format("Has been removed %d sentences in the text.", count));
    }

    @Override
    public long countVowels(TextComponentInformation component) throws TextException {
        if (component.getType() != SENTENCE) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        long i = countLetters(component, VOWEL_REGEX);
        logger.log(Level.INFO, "Has been counted Vowels. result = " + i);
        return i;
    }

    @Override
    public long countConsonants(TextComponentInformation component) throws TextException {
        if (component.getType() != SENTENCE) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        long i = countLetters(component, CONSONANT_REGEX);
        logger.log(Level.INFO, "Has been counted Consonants. result = " + i);
        return i;
    }

    private long countLetters(TextComponentInformation component, String reg) {
        long i = 0;
        i = component.getChildren().stream()
                .flatMap(w -> w.getChildren().stream())
                .filter(s -> s.toString().matches(reg))
                .count();
        return i;
    }

    @Override
    public List<TextComponentInformation> findSentencesOfMaxWord(TextComponentInformation component) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        int maxWordSize = 0;
        maxWordSize = findMaxWordSizeInText(component);
        logger.log(Level.INFO, "Has been found maxWordSize in text. result = " + maxWordSize);
        List<TextComponentInformation> list = new ArrayList<>();
        for (TextComponentInformation paragraph : component.getChildren()) {
            for (TextComponentInformation sentence : paragraph.getChildren()) {
                int tmpMaxWordSize = findMaxWordSizeInSentence(sentence);
                if (tmpMaxWordSize == maxWordSize) {
                    list.add(sentence);
                }
            }
        }
        logger.log(Level.INFO, "Has been found sentences with max wordSize. Count = " + list.size());
        return list;
    }

    private int findMaxWordSizeInText(TextComponentInformation component) {
        int maxWordSize = 0;
        for (TextComponentInformation paragraph : component.getChildren()) {
            for (TextComponentInformation sentence : paragraph.getChildren()) {
                int tmp = findMaxWordSizeInSentence(sentence);
                if (maxWordSize < tmp) {
                    maxWordSize = tmp;
                }
            }
        }
        return maxWordSize;
    }

    private int findMaxWordSizeInSentence(TextComponentInformation component) {
        int maxWordSize = 0;
        for (TextComponentInformation word : component.getChildren()) {
//            todo: first release
//            if (maxWordSize < word.getChildren().size()) {
//                maxWordSize = word.getChildren().size();
//            }
            int tmp = 0;
            for (TextComponentInformation leaf : word.getChildren()) {
                if (leaf.getType() != PUNCTUATION_LEAF) {
                    tmp++;
                }
                if (maxWordSize < tmp) {
                    maxWordSize = tmp;
                }
            }
        }
        return maxWordSize;
    }
}
