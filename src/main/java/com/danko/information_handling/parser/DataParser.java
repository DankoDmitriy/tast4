package com.danko.information_handling.parser;

import com.danko.information_handling.entity.TextComponentInformation;
import com.danko.information_handling.exception.TextException;

public interface DataParser {
    public TextComponentInformation parse(String data) throws TextException;
}
