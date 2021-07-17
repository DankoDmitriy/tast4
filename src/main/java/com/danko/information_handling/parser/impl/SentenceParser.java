package com.danko.information_handling.parser.impl;

import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.InformationComponent;
import com.danko.information_handling.entity.impl.TextComponent;
import com.danko.information_handling.exception.TextException;
import com.danko.information_handling.parser.DataParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements DataParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX = ".+?([.]{3}|[.!?])((\\r\\n)|$|\\s)";
    private DataParser parser = new LexemeParser();

    @Override
    public InformationComponent parse(String data) throws TextException {
        if (data == null || data.isBlank()) {
            throw new TextException("Input data is not correct...");
        }
        Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(data);
        InformationComponent paragraph = new TextComponent(ComponentType.PARAGRAPH);
        while (matcher.find()) {
            String s = matcher.group();
//            fixme - add to constants
            s = s.replaceAll("\\r\\n", " ");
            InformationComponent sentence = parser.parse(s);
            paragraph.addTextComponent(sentence);
        }
        logger.log(Level.INFO, "Sentences parsing is finished...");
        return paragraph;
    }
}
