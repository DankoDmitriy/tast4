package com.danko.information_handling.parser;

import com.danko.information_handling.creater.CreaterLeaf;
import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.TextComponentInformation;
import com.danko.information_handling.entity.impl.TextComponent;
import com.danko.information_handling.exception.TextException;
import com.danko.information_handling.parser.impl.LexemeParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LexemeParserTest {
    private DataParser parser;
    private TextComponentInformation sentence;
    private String testLine = "a b c";

    @BeforeClass
    public void createParser() {
        parser = new LexemeParser();
        sentence = new TextComponent(ComponentType.SENTENCE);
        TextComponentInformation tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreaterLeaf.createLeaf("a"));
        sentence.addTextComponent(tmp);

        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreaterLeaf.createLeaf("b"));
        sentence.addTextComponent(tmp);

        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreaterLeaf.createLeaf("c"));
        sentence.addTextComponent(tmp);
    }

    @Test
    public void lexemeParserPositiveTest() throws TextException {
        TextComponentInformation result = parser.parse(testLine);
        assertEquals(result, sentence);
    }
}
