package com.danko.information_handling.service;

import com.danko.information_handling.entity.InformationComponent;
import com.danko.information_handling.exception.TextException;
import com.danko.information_handling.parser.DataParser;
import com.danko.information_handling.parser.impl.LexemeParser;
import com.danko.information_handling.parser.impl.ParagraphParser;
import com.danko.information_handling.service.impl.TextComponentServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TextComponentServiceImplTest {
    private DataParser textParser;
    private TextComponentService service;
    private DataParser sentenceParser;

    @BeforeClass
    public void createParser() {
        textParser = new ParagraphParser();
        sentenceParser = new LexemeParser();
        service = new TextComponentServiceImpl();
    }

    @DataProvider(name = "data_text")
    public Object[][] createParagraphData() throws TextException {
        String str1 = "    Sentence1.    Sentence1. Sentence2.    Sentence1. Sentence2. Sentence3.";
        String str2 = "    Sentence1. Sentence2.    Sentence1.    Sentence1. Sentence2. Sentence3.";
        return new Object[][]{
                {textParser.parse(str1), textParser.parse(str2)}
        };
    }

    @Test(dataProvider = "data_text")
    public void sortParagraphsBySentencesTet(InformationComponent extend, InformationComponent component) throws TextException {
        service.sortParagraphsBySentences(component);
        assertEquals(extend, component);
    }

    @DataProvider(name = "equal_words")
    public Object[][] countEqualWordsData() throws TextException {
        String str = "    World is beautiful. There are a lot of countries in world.";
        return new Object[][]{
                {2, textParser.parse(str), "world"}
        };
    }

    @Test(dataProvider = "equal_words")
    public void countEqualWordsTest(Integer extend, InformationComponent component, String word) throws TextException {
        Integer result = service.countEqualWords(component, word);
        assertEquals(extend, result);
    }

    @DataProvider(name = "vowels")
    public Object[][] vowelsData() throws TextException {
        String str = "I love Java.";
        return new Object[][]{
                {5, sentenceParser.parse(str)}
        };
    }

    @Test(dataProvider = "vowels")
    public void countVowelsTest(long extend, InformationComponent component) throws TextException {
        long result = service.countVowels(component);
        assertEquals(extend, result);
    }

    @DataProvider(name = "consonants")
    public Object[][] consonantsData() throws TextException {
        String str = "Cat is big.";
        return new Object[][]{
                {5, sentenceParser.parse(str)}
        };
    }

    @Test(dataProvider = "consonants")
    public void countConsonantsTest(long extend, InformationComponent component) throws TextException {
        long result = service.countConsonants(component);
        assertEquals(extend, result);
    }

    @DataProvider(name = "find_sentences")
    public Object[][] findSentencesData() throws TextException {
        String str = "Cat is big.";
        String text = "    Cat is big. Cat is big. I am.";
        InformationComponent sentence1 = sentenceParser.parse(str);
        InformationComponent sentence2 = sentenceParser.parse(str);
        List<InformationComponent> sentences = List.of(sentence1, sentence2);
        return new Object[][]{
                {sentences, textParser.parse(text)}
        };
    }

    @Test(dataProvider = "find_sentences")
    public void findSentencesOfMaxWordTest(List<InformationComponent> extend, InformationComponent component) throws TextException {
        List<InformationComponent> result = service.findSentencesOfMaxWord(component);
        assertEquals(extend, result);
    }

    @DataProvider(name = "remove_sentences")
    public Object[][] removeSentencesData() throws TextException {
        String text = "    Cat is big. Cat is big.";
        String text1 = "    Cat is big. Cat is big. I am.";
        return new Object[][]{
                {textParser.parse(text), textParser.parse(text1), 3}
        };
    }

    @Test(dataProvider = "remove_sentences")
    public void removeSentencesWithMinWordsTest(InformationComponent extend, InformationComponent component, int minWords) throws TextException {
        service.removeSentencesWithMinWords(component, minWords);
        assertEquals(extend, component);
    }
}
