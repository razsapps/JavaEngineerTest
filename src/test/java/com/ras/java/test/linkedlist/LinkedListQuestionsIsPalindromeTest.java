package com.ras.java.test.linkedlist;

import com.ras.java.test.LinkedListQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import com.ras.java.test.core.SimpleListNode;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class LinkedListQuestionsIsPalindromeTest {
    @Test
    public void testExample() {
        assertTrue(LinkedListQuestions.isPalindrome(LinkedListUtil.createListFromString("1 -> 2 -> 3 -> 4 -> 3 -> 2 -> 1")));
    }

    @Test
    public void testEvenSizedList() {
        assertTrue(LinkedListQuestions.isPalindrome(LinkedListUtil.createListFromString("1 -> 2 -> 3 -> 4 -> 4 -> 3 -> 2 -> 1")));
    }

    @Test
    public void testSingleNode() {
        try {
            assertTrue(LinkedListQuestions.isPalindrome(new SimpleListNode(1)));
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testNotPalindrome() {
        assertFalse(LinkedListQuestions.isPalindrome(LinkedListUtil.createListFromString("1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 1")));
    }

    @Test
    public void testDefensiveNull() {
        try {
            assertFalse(LinkedListQuestions.isPalindrome(null));
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int size = 15000000;
        SimpleListNode node = LinkedListUtil.createListForEachNumber(size);
        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(LinkedListQuestions.class)
                .setMethodName("isPalindrome")
                .setParameterTypes(SimpleListNode.class)
                .setInputs(node)
                .testEfficient();
    }

    public static boolean isPalindrome(SimpleListNode node) {
        if (node == null)
            throw new IllegalArgumentException("You must provide a list.");

        SimpleListNode reverseHalf = null;
        SimpleListNode singleRunner = node;
        SimpleListNode doubleRunner = node.getNext();
        boolean odd = true;

        //Let's get singleRunner to the halfway mark
        while (doubleRunner != null) {
            //If we aren't at the end of the list end the singleRunner to a reverse list
            SimpleListNode duplicateNode = new SimpleListNode(singleRunner.getValue());
            duplicateNode.setNext(reverseHalf);
            reverseHalf = duplicateNode;

            //Increment singleRunner
            singleRunner = singleRunner.getNext();
            doubleRunner = doubleRunner.getNext();

            //Move this twice as fast
            if (doubleRunner != null) {
                doubleRunner = doubleRunner.getNext();
                odd = true;
            }
            else
                odd = false;
        }

        //Remove the first element in singleRunner since that is equals to itself
        if (odd)
            singleRunner = singleRunner.getNext();

        //Compare the last half to the reverse of the first half
        while (singleRunner != null) {
            if (singleRunner.getValue() != reverseHalf.getValue())
                return false;
            singleRunner = singleRunner.getNext();
            reverseHalf = reverseHalf.getNext();
        }
        return true;
    }
}
