package com.danko.information_handling.entity;

import java.util.List;

public interface TextComponentInformation {
    List<TextComponentInformation> getChildren();

    void addTextComponent(TextComponentInformation component);

    void removeTextComponent(TextComponentInformation component);

    ComponentType getType();
}
