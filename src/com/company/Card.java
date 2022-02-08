package com.company;

public class Card {
    char face;
    char suit;
    int value;

    public char getSuit() {
        return suit;
    }
    public void setSuit(char suit) {
        this.suit = suit;
    }

    public char getFace() {
        return face;
    }
    public void setFace(char face) {
        this.face = face;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public Card(char face, char suit, int value) {
        this.suit = suit;
        this.face = face;
        this.value = value;
    }

    void print() {
        System.out.printf("%s%s ",
                this.face, this.suit);
    }
}