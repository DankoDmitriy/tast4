package com.danko.information_handling.entity.impl;

import com.danko.information_handling.entity.ComponentType;
import com.danko.information_handling.entity.InformationComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComponent implements InformationComponent {
    private static Logger logger = LogManager.getLogger();
    private static final String TABULATION_REGEX = "\t";
    private List<InformationComponent> components = new ArrayList<>();
    private ComponentType type;

    public TextComponent(ComponentType type) {
        this.type = type;
    }

    @Override
    public List<InformationComponent> getChildren() {
        return components;
    }

    @Override
    public void addTextComponent(InformationComponent component) {
        components.add(component);
    }

    @Override
    public void removeTextComponent(InformationComponent component) {
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
        StringBuilder sb = new StringBuilder();
        String delimiter = type.getDelimiter();
        for (InformationComponent component : components) {
            if (type == ComponentType.TEXT) {
                sb.append(TABULATION_REGEX);
            }
            sb.append(component.toString()).append(delimiter);
        }
        return sb.toString();
    }
}
