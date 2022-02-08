package com.company;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Pairs {

    public static int[] twoPairs(ArrayList<Card> thisHand) {
        Predicate<Card> twoPairs = card
                -> (card.value == thisHand.get(2).value);
        Predicate<Card> twoPairsL = card
                -> (card.value == thisHand.get(0).value);
        int[] orderArr = {0,0,0};

        // if the middle is single
        if (thisHand.stream().filter(twoPairs).count() == 1) {
            orderArr[0] = thisHand.get(4).value;
            orderArr[1] = thisHand.get(0).value;
            orderArr[2] = thisHand.get(2).value;
        } else { // if the middle is paired
            // and if the bottom is single
            if (thisHand.stream().filter(twoPairsL).count() == 1) {
                orderArr[0] = thisHand.get(4).value;
                orderArr[1] = thisHand.get(2).value;
                orderArr[2] = thisHand.get(0).value;
            } else { // or if the top is single
                orderArr[0] = thisHand.get(2).value;
                orderArr[1] = thisHand.get(0).value;
                orderArr[2] = thisHand.get(4).value;
            }
        }
        return orderArr;
    }

    public static int[] onePair(ArrayList<Card> thisHand) {
        Predicate<Card> pairA = card
                -> (card.value == thisHand.get(1).value);
        Predicate<Card> pairB = card
                -> (card.value == thisHand.get(2).value);
        Predicate<Card> pairC = card
                -> (card.value == thisHand.get(3).value);

        int[] orderArr = new int[]{0, 0, 0, 0};

        // if pos 1 is paired
        if (thisHand.stream().filter(pairA).count() == 2) {
            // and if pos 2 is paired (1,2)
            if (thisHand.stream().filter(pairB).count() == 2) {
                orderArr[0] = thisHand.get(2).value;
                orderArr[1] = thisHand.get(4).value;
                orderArr[2] = thisHand.get(3).value;
                orderArr[3] = thisHand.get(0).value;
            } else { // or if pos 2 is single (0,1)
                orderArr[0] = thisHand.get(1).value;
                orderArr[1] = thisHand.get(4).value;
                orderArr[2] = thisHand.get(3).value;
                orderArr[3] = thisHand.get(2).value;
            }
        // if pos 3 is paired
        } else if (thisHand.stream().filter(pairC).count() == 2) {
            // and if pos 2 is paired (2,3)
            if (thisHand.stream().filter(pairB).count() == 2) {
                orderArr[0] = thisHand.get(3).value;
                orderArr[1] = thisHand.get(4).value;
                orderArr[2] = thisHand.get(1).value;
                orderArr[3] = thisHand.get(0).value;
            } else { // or if pos 2 is single (3,4)
                orderArr[0] = thisHand.get(4).value;
                orderArr[1] = thisHand.get(2).value;
                orderArr[2] = thisHand.get(1).value;
                orderArr[3] = thisHand.get(0).value;
            }
        }
        return orderArr;
    }
}
