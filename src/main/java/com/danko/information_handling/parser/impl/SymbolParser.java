package com.danko.information_handling.parser.impl;

import com.danko.information_handling.creater.CreaterLeaf;
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

public class SymbolParser implements DataParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SYMBOL_REGEX = ".";

    @Override
    public InformationComponent parse(String lexeme) throws TextException {
        Pattern pattern = Pattern.compile(SYMBOL_REGEX);
        Matcher matcher = pattern.matcher(lexeme);
        InformationComponent word = new TextComponent(ComponentType.WORD);
        while (matcher.find()) {
            InformationComponent leaf = CreaterLeaf.createLeaf(matcher.group());
            word.addTextComponent(leaf);
        }
        logger.log(Level.INFO, "Symbols parsing is finished...");
        return word;
    }
}
