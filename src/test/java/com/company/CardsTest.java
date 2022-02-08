package test.java.com.company;

import main.java.com.company.Card;
import main.java.com.company.Sort;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import static main.java.com.company.Sort.*;
import static main.java.com.company.Pairs.*;

public class CardsTest {

    @Test
    public void testValue() { // checks a few card values from input string
        Assert.assertEquals(4, Sort.giveValue("4H"));
        Assert.assertEquals(13, Sort.giveValue("KC"));
        Assert.assertEquals(11, Sort.giveValue("JD"));
    }

    @Test
    public void testScore() { // checks a few hand types, e.g. flush, straight, pair
        ArrayList<Card> testHand = new ArrayList<>();

        Card a = new Card('3','H',3);
        Card b = new Card('4','D',4);
        Card c = new Card('5','S',5);
        Card d = new Card('6','C',6);
        Card e = new Card('7','D',7);
        testHand.addAll(Arrays.asList(a,b,c,d,e));
        Assert.assertEquals(5, score(testHand));

        a = new Card('2','H',2);
        b = new Card('3','C',3);
        c = new Card('8','H',8);
        d = new Card('8','D',8);
        e = new Card('K','D',13);
        testHand.clear();
        testHand.addAll(Arrays.asList(a,b,c,d,e));
        Assert.assertEquals(2, score(testHand));

        a = new Card ('2','D',2);
        b = new Card ('3','D',3);
        c = new Card ('4','D',4);
        d = new Card ('5','D',5);
        e = new Card ('6','D',6);
        testHand.clear();
        testHand.addAll(Arrays.asList(a,b,c,d,e));
        Assert.assertEquals(9, score(testHand));

        e = new Card ('A','D',14);
        testHand.clear();
        testHand.addAll(Arrays.asList(e,a,b,c,d));
        Assert.assertEquals(6, score(testHand));

        d = new Card ('A','H',14);
        b = new Card ('4','C',4);
        a = new Card ('4','S',4);
        testHand.clear();
        testHand.addAll(Arrays.asList(a,b,c,d,e));
        Assert.assertEquals(7, score(testHand));
    }

    @Test
    public void testCompareHands() { // checks hands are correctly scored
        ArrayList<Card> LeftHand = new ArrayList<>();
        ArrayList<Card> RightHand = new ArrayList<>();

        Card a = new Card('2','H',2);
        Card b = new Card('3','C',3);
        Card c = new Card('8','H',8);
        Card d = new Card('8','D',8);
        Card e = new Card('K','D',13);
        LeftHand.addAll(Arrays.asList(a,b,c,d,e));

        Card v = new Card('2','D',2);
        Card w = new Card('4','S',4);
        Card x = new Card('8','C',8);
        Card y = new Card('8','S',8);
        Card z = new Card('K','H',13);
        RightHand.addAll(Arrays.asList(v,w,x,y,z));

        int[] check = {0,1}; // right hand should win
        Assert.assertArrayEquals(check, compareHands(LeftHand, RightHand));

        Card f = new Card('9','S',9);
        Card g = new Card('T','D',10);
        Card h = new Card('J','C',11);
        Card i = new Card('J','H',11);
        Card j = new Card('K','S',13);
        LeftHand.clear();
        LeftHand.addAll(Arrays.asList(f,g,h,i,j));

        check = new int[]{1,1}; // left hand should win next
        Assert.assertArrayEquals(check, compareHands(LeftHand, RightHand));

        g = new Card('9','H',9);
        j = new Card('J','C',11);
        LeftHand.clear();
        LeftHand.addAll(Arrays.asList(f,g,h,i,j));

        v = new Card('4','D',4);
        z = new Card('8','H',8);
        RightHand.clear();
        RightHand.addAll(Arrays.asList(v,w,x,y,z));

        check = new int[]{2,1}; // left hand should win again
        Assert.assertArrayEquals(check, compareHands(LeftHand, RightHand));
    }

    @Test
    public void testPairs() { // checks pair comparison array values
        ArrayList<Card> testHand = new ArrayList<>();

        Card f = new Card('9','S',9);
        Card g = new Card('T','D',10);
        Card h = new Card('J','C',11);
        Card i = new Card('J','H',11);
        Card j = new Card('K','S',13);
        testHand.addAll(Arrays.asList(f,g,h,i,j));

        int[] checkArr = new int[]{11,13,10,9}; // single pair first
        Assert.assertArrayEquals(checkArr, onePair(testHand));

        f = new Card('T','H',10);
        testHand.clear();
        testHand.addAll(Arrays.asList(f,g,h,i,j));
        checkArr = new int[]{11,10,13}; // higher pair, lower pair, spare
        Assert.assertArrayEquals(checkArr, twoPairs(testHand));
    }
}
