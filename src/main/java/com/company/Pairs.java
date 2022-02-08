package main.java.com.company;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Pairs {

    public static int[] twoPairs(ArrayList<Card> thisHand) {
        int orderArr[] = new int[3];

        // if the top is paired
        if (thisHand.get(4).value == thisHand.get(3).value) {
            // if the bottom is paired
            if (thisHand.get(0).value == thisHand.get(1).value) {
                orderArr[0] = thisHand.get(4).value;
                orderArr[1] = thisHand.get(0).value;
                orderArr[2] = thisHand.get(2).value;
            } else { // if the middle is paired
                orderArr[0] = thisHand.get(4).value;
                orderArr[1] = thisHand.get(2).value;
                orderArr[2] = thisHand.get(0).value;
            }
        } else { // or if the top is single
            orderArr[0] = thisHand.get(2).value;
            orderArr[1] = thisHand.get(0).value;
            orderArr[2] = thisHand.get(4).value;
        }
        return orderArr;
    }

    public static int[] onePair(ArrayList<Card> thisHand) {

        int[] orderArr = new int[]{0, 0, 0, 0};

        if (thisHand.get(0).value == thisHand.get(1).value) {
            orderArr[0] = thisHand.get(1).value;
            orderArr[1] = thisHand.get(4).value;
            orderArr[2] = thisHand.get(3).value;
            orderArr[3] = thisHand.get(2).value;
        } else if (thisHand.get(1).value == thisHand.get(2).value) {
            orderArr[0] = thisHand.get(2).value;
            orderArr[1] = thisHand.get(4).value;
            orderArr[2] = thisHand.get(3).value;
            orderArr[3] = thisHand.get(0).value;
        } else if (thisHand.get(2).value == thisHand.get(3).value) {
            orderArr[0] = thisHand.get(3).value;
            orderArr[1] = thisHand.get(4).value;
            orderArr[2] = thisHand.get(1).value;
            orderArr[3] = thisHand.get(0).value;
        } else { // pair at (3,4)
            orderArr[0] = thisHand.get(4).value;
            orderArr[1] = thisHand.get(2).value;
            orderArr[2] = thisHand.get(1).value;
            orderArr[3] = thisHand.get(0).value;
        }
        return orderArr;
    }
}
