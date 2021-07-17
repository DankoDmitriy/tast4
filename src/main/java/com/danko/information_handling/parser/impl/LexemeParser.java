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

public class LexemeParser implements DataParser {
    private static Logger logger = LogManager.getLogger();
    private static final String LEXEME_REGEX = "\\s?(.*?)($|\\s)";
    private DataParser parser = new SymbolParser();

    @Override
    public InformationComponent parse(String data) throws TextException {
        if (data == null || data.isBlank()) {
            throw new TextException("Input data is not correct...");
        }
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(data);
        InformationComponent sentence = new TextComponent(ComponentType.SENTENCE);
        String lexeme;
        while (matcher.find()) {
            lexeme = matcher.group(1);
            if (lexeme.length() > 0) {
                InformationComponent word = parser.parse(lexeme);
                sentence.addTextComponent(word);
            }
        }
        logger.log(Level.INFO, "Lexemes parsing is finished...");
        return sentence;
    }
}
