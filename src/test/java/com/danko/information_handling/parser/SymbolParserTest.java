package com.danko.information_handling.parser;

import com.danko.information_handling.creater.CreaterLeaf;
import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.InformationComponent;
import com.danko.information_handling.entity.impl.TextComponent;
import com.danko.information_handling.exception.TextException;
import com.danko.information_handling.parser.impl.SymbolParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SymbolParserTest {
    private DataParser parser;
    private InformationComponent word;
    private String testLine = "a";

    @BeforeClass
    public void createParser() {
        parser = new SymbolParser();
        word = new TextComponent(ComponentType.WORD);
        word.addTextComponent(CreaterLeaf.createLeaf("a"));
    }

    @Test
    public void symbolParserPositiveTest() throws TextException {
        InformationComponent result = parser.parse(testLine);
        assertEquals(result, word);
    }
}
