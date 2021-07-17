package com.danko.information_handling.entity;

public enum ComponentType {
    PARAGRAPH(" "),
    SENTENCE(" "),
    WORD(""),
    SYMBOL_LEAF(""),
    PUNCTUATION_LEAF(""),
    TEXT("\r\n");

    private final String delimiter;

    ComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
