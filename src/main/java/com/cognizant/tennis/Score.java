package com.cognizant.tennis;

public enum Score {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40);
    
    private int value;

    Score(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
    
    public Score getNext() {
        return values()[(ordinal() + 1) % values().length];
    }
	
}