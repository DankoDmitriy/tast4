package com.danko.information_handling.service.impl;

import com.danko.information_handling.comparator.SentencesCountComparator;
import com.danko.information_handling.entity.InformationComponent;
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
    public int countEqualWords(InformationComponent component, String searchWord) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        int count = 0;
        List<InformationComponent> paragraphs = component.getChildren();
        for (InformationComponent paragraph : paragraphs) {
            List<InformationComponent> sentences = paragraph.getChildren();
            for (InformationComponent sentence : sentences) {
                List<InformationComponent> words = sentence.getChildren();
                for (InformationComponent word : words) {
                    List<InformationComponent> leafs = word.getChildren();
                    StringBuilder sb = new StringBuilder();
                    String collectedWord = "";
                    for (InformationComponent leaf : leafs) {
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

    public void sortParagraphsBySentences(InformationComponent component) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        List<InformationComponent> paragraphs = component.getChildren();
        paragraphs.sort(new SentencesCountComparator());
    }

    @Override
    public void removeSentencesWithMinWords(InformationComponent component, int minWords) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        int count = 0;
        List<InformationComponent> paragraphs = component.getChildren();
        for (InformationComponent paragraph : paragraphs) {
            List<InformationComponent> sentences = paragraph.getChildren();
            int tmpStartSize = sentences.size();
            sentences.removeIf(s -> s.getChildren().size() < minWords);
            count += tmpStartSize - sentences.size();
        }
        logger.log(Level.INFO, String.format("Has been removed %d sentences in the text.", count));
    }

    @Override
    public long countVowels(InformationComponent component) throws TextException {
        if (component.getType() != SENTENCE) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        long i = countLetters(component, VOWEL_REGEX);
        logger.log(Level.INFO, "Has been counted Vowels. result = " + i);
        return i;
    }

    @Override
    public long countConsonants(InformationComponent component) throws TextException {
        if (component.getType() != SENTENCE) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        long i = countLetters(component, CONSONANT_REGEX);
        logger.log(Level.INFO, "Has been counted Consonants. result = " + i);
        return i;
    }

    private long countLetters(InformationComponent component, String reg) {
        long i = 0;
        i = component.getChildren().stream()
                .flatMap(w -> w.getChildren().stream())
                .filter(s -> s.toString().matches(reg))
                .count();
        return i;
    }

    @Override
    public List<InformationComponent> findSentencesOfMaxWord(InformationComponent component) throws TextException {
        if (component.getType() != TEXT) {
            logger.log(Level.ERROR, "Input data type is not correct" + component.getType());
            throw new TextException("Input data type is not correct" + component.getType());
        }
        int maxWordSize = 0;
        maxWordSize = findMaxWordSizeInText(component);
        logger.log(Level.INFO, "Has been found maxWordSize in text. result = " + maxWordSize);
        List<InformationComponent> list = new ArrayList<>();
        for (InformationComponent paragraph : component.getChildren()) {
            for (InformationComponent sentence : paragraph.getChildren()) {
                int tmpMaxWordSize = findMaxWordSizeInSentence(sentence);
                if (tmpMaxWordSize == maxWordSize) {
                    list.add(sentence);
                }
            }
        }
        logger.log(Level.INFO, "Has been found sentences with max wordSize. Count = " + list.size());
        return list;
    }

    private int findMaxWordSizeInText(InformationComponent component) {
        int maxWordSize = 0;
        for (InformationComponent paragraph : component.getChildren()) {
            for (InformationComponent sentence : paragraph.getChildren()) {
                int tmp = findMaxWordSizeInSentence(sentence);
                if (maxWordSize < tmp) {
                    maxWordSize = tmp;
                }
            }
        }
        return maxWordSize;
    }

    private int findMaxWordSizeInSentence(InformationComponent component) {
        int maxWordSize = 0;
        for (InformationComponent word : component.getChildren()) {
            int tmp = 0;
            for (InformationComponent leaf : word.getChildren()) {
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
