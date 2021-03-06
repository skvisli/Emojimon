package com.progark.emojimon.model.interfaces;

public interface Die {

    void roll();

    int getValue();

    void setValue(int value);

    void setUsed(boolean used);

    boolean getUsed();
}
