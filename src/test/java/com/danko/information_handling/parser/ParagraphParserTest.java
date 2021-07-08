package com.danko.information_handling.parser;

import com.danko.information_handling.creater.CreaterLeaf;
import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.TextComponentInformation;
import com.danko.information_handling.entity.impl.TextComponent;
import com.danko.information_handling.exception.TextException;
import com.danko.information_handling.parser.impl.ParagraphParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ParagraphParserTest {
    private DataParser parser;
    private TextComponentInformation text;
    private String testLine = "    a b. a.";

    @Test
    public void paragraphParserPositiveTest() throws TextException {
        TextComponentInformation result = parser.parse(testLine);
        assertEquals(result, text);
    }

    @BeforeClass
    public void createParser() {
        parser = new ParagraphParser();
        text = new TextComponent(ComponentType.TEXT);

        TextComponentInformation paragraph = new TextComponent(ComponentType.PARAGRAPH);

        TextComponentInformation sentence = new TextComponent(ComponentType.SENTENCE);
        TextComponentInformation tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreaterLeaf.createLeaf("a"));
        sentence.addTextComponent(tmp);

        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreaterLeaf.createLeaf("b"));
        tmp.addTextComponent(CreaterLeaf.createLeaf("."));
        sentence.addTextComponent(tmp);

        paragraph.addTextComponent(sentence);

        sentence = new TextComponent(ComponentType.SENTENCE);
        tmp = new TextComponent(ComponentType.WORD);
        tmp.addTextComponent(CreaterLeaf.createLeaf("a"));
        tmp.addTextComponent(CreaterLeaf.createLeaf("."));
        sentence.addTextComponent(tmp);

        paragraph.addTextComponent(sentence);

        text.addTextComponent(paragraph);
    }
}
