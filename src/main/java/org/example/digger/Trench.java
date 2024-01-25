package org.example.digger;


public class Trench {
    static int givenLength = 5;
    protected int currentLength = 0;

    public Trench() {
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }
}

