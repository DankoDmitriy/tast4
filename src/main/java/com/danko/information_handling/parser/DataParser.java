package com.danko.information_handling.parser;

import com.danko.information_handling.entity.InformationComponent;
import com.danko.information_handling.exception.TextException;

public interface DataParser {
    public InformationComponent parse(String data) throws TextException;
}
