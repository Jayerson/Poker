package main.java.com.company;

public class Card {
    char face;
    char suit;
    int value;

    public int getValue() {
        return value;
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