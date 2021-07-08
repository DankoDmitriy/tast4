package com.danko.information_handling.parser.impl;

import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.TextComponentInformation;
import com.danko.information_handling.entity.impl.TextComponent;
import com.danko.information_handling.exception.TextException;
import com.danko.information_handling.parser.DataParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParagraphParser implements DataParser {
    private static Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX = "(\\s{4})";
    private static final int EMPTY_PARAGRAPH_POSITION = 0;
    private DataParser parser = new SentenceParser();

    @Override
    public TextComponentInformation parse(String data) throws TextException {
        if (data == null || data.isBlank()) {
            throw new TextException("Input data is not correct...");
        }
        String[] paragraphs = data.split(PARAGRAPH_REGEX);
        List<String> paragraphsList = new ArrayList<>(Arrays.asList(paragraphs));
        paragraphsList.remove(EMPTY_PARAGRAPH_POSITION);
        TextComponentInformation text = new TextComponent(ComponentType.TEXT);
        for (String paragraph : paragraphsList) {
            TextComponentInformation tmpParagraph = parser.parse(paragraph);
            text.addTextComponent(tmpParagraph);
        }
        logger.log(Level.INFO, "Paragraphs parsing is finished...");
        return text;
    }
}
