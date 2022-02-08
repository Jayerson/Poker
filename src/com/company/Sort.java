package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Sort {

    static ArrayList<ArrayList<Card>> leftHands = new ArrayList<>();
    static ArrayList<Card> thisLeftHand = new ArrayList<>();
    static ArrayList<ArrayList<Card>> rightHands = new ArrayList<>();
    static ArrayList<Card> thisRightHand = new ArrayList<>();

    static int[] finalScoreCount = {0,0};

    // nested arraylists keeps hands together

    // cards from file - no definitions, only parsing

    public static int giveValue(String card) {
        int value = 0;
        if (card.charAt(0) < 58 || card.charAt(0) > 48) {
            value = card.charAt(0) -48;
        }
        value = switch (card.split("")[0]) {
            case "T" -> 10;
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            case "A" -> 14;
            default -> value;
        };
        return value;
    }

    public static int score(ArrayList<Card> thisHand) {

        int score;
        // flushes
        Predicate<Card> matchSuit = card -> (card.suit == thisHand.get(0).suit);
        if (thisHand.stream().filter(matchSuit).count() == 5) {
            // royal flush
            if (thisHand.get(0).value == 10 && thisHand.get(1).value == 11 &&
                    thisHand.get(2).value == 12 && thisHand.get(3).value == 13 &&
                    thisHand.get(4).value == 14) {
                score = 10;
            // straight flush
            } else if (thisHand.get(0).value == thisHand.get(1).value - 1 &&
                    thisHand.get(1).value == thisHand.get(2).value - 1 &&
                    thisHand.get(2).value == thisHand.get(3).value - 1 &&
                    thisHand.get(3).value == thisHand.get(4).value - 1) {
                score = 9;
            // plain flush
            } else { score = 6; }
        } else {
            Predicate<Card> count3plus = card -> (card.value == thisHand.get(2).value);
            // four of a kind
            if (thisHand.stream().filter(count3plus).count() == 4) {
                score = 8;
            // full house
            } else if (thisHand.stream().filter(count3plus).count() == 3 &&
                    ( (thisHand.get(0).value == thisHand.get(1).value &&
                            thisHand.get(0).value != thisHand.get(2).value) ||
                    (thisHand.get(3).value == thisHand.get(4).value &&
                            thisHand.get(4).value != thisHand.get(2).value) )
            ) {
                score = 7;
            // straight, no flush
            } else if (thisHand.get(0).value == thisHand.get(1).value - 1 &&
                    thisHand.get(1).value == thisHand.get(2).value - 1 &&
                    thisHand.get(2).value == thisHand.get(3).value - 1 &&
                    thisHand.get(3).value == thisHand.get(4).value - 1) {
                score = 5;
            // three of a kind
            } else if (thisHand.stream().filter(count3plus).count() == 3) {
                score = 4;
            // two pair
            } else if ( (thisHand.get(0).value == thisHand.get(1).value
                    || thisHand.get(1).value == thisHand.get(2).value) &&
                    (thisHand.get(2).value == thisHand.get(3).value
                    || thisHand.get(3).value == thisHand.get(4).value) ) {
                score = 3;
            // single pair
            } else if (thisHand.get(0).value == thisHand.get(1).value
                    || thisHand.get(1).value == thisHand.get(2).value
                    || thisHand.get(2).value == thisHand.get(3).value
                    || thisHand.get(3).value == thisHand.get(4).value) {
                score = 2;
            } else score = 1;
        }
        return score;
    }

    public static void compareHands() {

        // if the scores are different
        if (score(thisLeftHand) > score(thisRightHand)) {
            finalScoreCount[0]++;
        } else if (score(thisLeftHand) < score(thisRightHand)) {
            finalScoreCount[1]++;
        } else {
            switch (score(thisLeftHand)) { // if the scores match

                case 5, 7, 8, 9, 10:
                    // compare higher rank first - some wash out quick
                    if (thisLeftHand.get(2).value > thisRightHand.get(2).value) {
                        finalScoreCount[0]++;
                    } else if (thisLeftHand.get(2).value < thisRightHand.get(2).value) {
                        finalScoreCount[1]++;
                    } else { // compare the low-ranking value
                        int l2 = thisLeftHand.get(2).value == thisLeftHand.get(4).value ?
                                thisLeftHand.get(4).value : thisLeftHand.get(0).value;
                        int r2 = thisRightHand.get(2).value == thisRightHand.get(4).value ?
                                thisRightHand.get(4).value : thisRightHand.get(0).value;
                        if (l2 > r2) {
                            finalScoreCount[0]++;
                        } else {
                            finalScoreCount[1]++;
                        }
                    }
                    break;

                case 4: // three of a kind
                    if (thisLeftHand.get(2).value > thisRightHand.get(2).value) {
                        finalScoreCount[0]++;
                        System.out.print("\n");
                    } else if (thisLeftHand.get(2).value < thisRightHand.get(2).value) {
                        finalScoreCount[1]++;
                        System.out.print("\n");
                    } else { // get non-ranking values
                        int[] leftArr = {0, 0};
                        int[] rightArr = {0, 0};
                        for (int i = 4; i >= 0; i--) {
                            if (thisLeftHand.get(i).value != thisLeftHand.get(2).value) {
                                if (leftArr[0] == 0) {
                                    leftArr[0] = thisLeftHand.get(i).value;
                                } else {
                                    leftArr[1] = thisLeftHand.get(i).value;
                                }
                            }
                            if (thisRightHand.get(i).value != thisRightHand.get(2).value) {
                                if (rightArr[0] == 0) {
                                    rightArr[0] = thisRightHand.get(i).value;
                                } else {
                                    rightArr[1] = thisRightHand.get(i).value;
                                }
                            }
                        } // then compare
                        if (leftArr[0] > rightArr[0]) {
                            finalScoreCount[0]++;
                        } else if (leftArr[0] < rightArr[0]) {
                            finalScoreCount[1]++;
                        } else {
                            if (leftArr[1] > rightArr[1]) {
                                finalScoreCount[0]++;
                            } else if (leftArr[1] < rightArr[1]) {
                                finalScoreCount[1]++;
                            }
                        }
                        // System.out.printf(" : %d %d vs %d %d : ", leftArr, rightArr);
                    }
                    break;

                case 3: // two pair - 3 values each
                    int[] leftArr = Pairs.twoPairs(thisLeftHand);
                    int[] rightArr = Pairs.twoPairs(thisRightHand);

                    // comparison
                    for (int i = 0; i < 3; i++) {
                        if (leftArr[i] > rightArr[i]) {
                            finalScoreCount[0]++;
                            break;
                        }
                        if (leftArr[i] < rightArr[i]) {
                            finalScoreCount[1]++;
                            break;
                        }
                    }
//                    System.out.printf("%d %d %d vs %d %d %d : ",
//                            leftArr[0], leftArr[1], leftArr[2],
//                            rightArr[0], rightArr[1], rightArr[2]);
                    break;

                case 2: // pair - 4 values
                    leftArr = Pairs.onePair(thisLeftHand);
                    rightArr = Pairs.onePair(thisRightHand);

                    // ready, steady, loop!
                    for (int i = 0; i < 4; i++) {
                        if (leftArr[i] > rightArr[i]) {
                            finalScoreCount[0]++;
                            break;
                        }
                        if (leftArr[i] < rightArr[i]) {
                            finalScoreCount[1]++;
                            break;
                        }
                    }
//                    System.out.printf("%d %d %d %d vs %d %d %d %d : ",
//                            leftArr[0], leftArr[1], leftArr[2], leftArr[3],
//                            rightArr[0], rightArr[1], rightArr[2], rightArr[3]);
                    break;
                default:
                    for (int i = 4; i >= 0; i--) {
                        if (thisLeftHand.get(i).value > thisRightHand.get(i).value) {
                            finalScoreCount[0]++;
                            break;
                        }
                        if (thisLeftHand.get(i).value < thisRightHand.get(i).value) {
                            finalScoreCount[1]++;
                            break;
                        }
                    }
                // end switch
            }
        }
//        System.out.printf("Player 1: %d hands, Player 2: %d hands\n",
//                finalScoreCount[0], finalScoreCount[1]);
    }

    public static void makeHands() {

        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();

        FileReader hands = null;
        try {
            hands = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // initial test file input
        Scanner inFile = new Scanner(hands);

        while (inFile.hasNextLine()) {
            String cards = inFile.nextLine();
            String[] cardsArr = cards.split(" ");
            // split into hands
            String[] hand1 = new String[5];
            String[] hand2 = new String[5];
            thisRightHand.clear();
            thisLeftHand.clear();

            for (int i = 0; i < 5; i++) {
                hand1[i] = cardsArr[i + 5];
                Card right = new Card(hand1[i].charAt(0),
                        hand1[i].charAt(1), giveValue(hand1[i]));
                thisRightHand.add(right);
            }
            thisRightHand.sort(Comparator.comparing(Card::getValue));
            rightHands.add(thisRightHand);

            for (int i = 0; i < 5; i++) {
                hand2[i] = cardsArr[i];
                Card left = new Card(hand2[i].charAt(0),
                        hand2[i].charAt(1), giveValue(hand2[i]));
                thisLeftHand.add(left);
            }
            thisLeftHand.sort(Comparator.comparing(Card::getValue));
            leftHands.add(thisLeftHand);

//            for (Card card : thisLeftHand) {
//                card.print();
//            }
//            System.out.print(": ");
//            for (Card card : thisRightHand) {
//                card.print();
//            }
//            System.out.printf(": %s vs %s : ", score(thisLeftHand), score(thisRightHand));

            compareHands();
        }
        System.out.printf("Player 1: %d hands\nPlayer 2: %d hands",
                finalScoreCount[0], finalScoreCount[1]);
    }
}
