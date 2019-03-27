package com.progark.emojimon.model.factories;

import com.progark.emojimon.model.BasicCanClearStrategy;
import com.progark.emojimon.model.strategyPattern.CanClearStrategy;

public class CanClearStrategyFactory {

    // Create a concrete strategy for bearing off (clearing pieces)
    public CanClearStrategy getCanClearStrategy(String canClear){
        if (canClear == null){
            return null;
        }
        if (canClear.equalsIgnoreCase("BASIC")){
            return new BasicCanClearStrategy();
        }
        return null;
    }
}
