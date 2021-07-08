package com.danko.information_handling.entity.impl;

import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.TextComponentInformation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LetterLeaf implements TextComponentInformation {
    private static Logger logger = LogManager.getLogger();
    private ComponentType type;
    private char symbol;

    public LetterLeaf(ComponentType type, char symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    @Override
    public List<TextComponentInformation> getChildren() {
        logger.log(Level.ERROR, "Unsupported operation get children on leaf");
        throw new UnsupportedOperationException("Unsupported operation get children on leaf");
    }

    @Override
    public void addTextComponent(TextComponentInformation component) {
        logger.log(Level.ERROR, "Unsupported operation add on leaf");
        throw new UnsupportedOperationException("Unsupported operation add on leaf");
    }

    @Override
    public void removeTextComponent(TextComponentInformation component) {
        logger.log(Level.ERROR, "Unsupported operation remove on leaf");
        throw new UnsupportedOperationException("Unsupported operation remove on leaf");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LetterLeaf that = (LetterLeaf) o;

        if (symbol != that.symbol) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (int) symbol;
        return result;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
