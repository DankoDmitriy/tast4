package com.danko.information_handling.entity.impl;

import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.TextComponentInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComponent implements TextComponentInformation {
    private static Logger logger = LogManager.getLogger();
    private List<TextComponentInformation> components = new ArrayList<>();
    private ComponentType type;

    public TextComponent(ComponentType type) {
        this.type = type;
    }

    @Override
    public List<TextComponentInformation> getChildren() {
        return components;
    }

    @Override
    public void addTextComponent(TextComponentInformation component) {
        components.add(component);
    }

    @Override
    public void removeTextComponent(TextComponentInformation component) {
        components.remove(component);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComponent that = (TextComponent) o;

        if (components != null ? !components.equals(that.components) : that.components != null) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = components != null ? components.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextComponent{");
        sb.append("components=").append(components);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
