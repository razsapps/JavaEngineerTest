package com.ras.java.test;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by RAS on 11/23/2014.
 */
public class ArrayQuestionsTest {
    @Test
    public void testIsPalindrome() {
        assertTrue("Failed for input 'Noel sees Leon'", ArrayQuestions.isPalindrome("Noel sees Leon".toCharArray()));
        assertTrue("Failed for input 'Too hot to hoot'", ArrayQuestions.isPalindrome("Too hot to hoot".toCharArray()));
        assertFalse("Failed for invalid input 'Richard So'", ArrayQuestions.isPalindrome("Richard So".toCharArray()));
    }

    @Test
    public void testIsPermutation() {
        assertTrue("Failed for input 'aBc12' and 'Bc1a2'", ArrayQuestions.isPermutation("aBc12".toCharArray(), "Bc1a2".toCharArray()));
        assertTrue("Failed for input 'You are Correct!' and 'CYo !areuec rrt'", ArrayQuestions.isPermutation("You are Correct!".toCharArray(), "CYo !areuec rrt".toCharArray()));
        assertFalse("Failed for invalid input 'You are' 'Wrong'", ArrayQuestions.isPermutation("You are".toCharArray(), "Wrong".toCharArray()));
    }

    @Test
    public void testMakePositive() {
        String[] input = new String[]{"This", "project", "sucks", "Rich", "sucks", null, null};
        String[] expected = new String[]{"This", "project", "is", "awesome", "Rich", "is", "awesome"};
        ArrayQuestions.makePositive(input);
        assertArrayEquals(expected, input);
        input = new String[]{"This", "question", "sucks", null};
        expected = new String[]{"This", "question", "is", "awesome"};
        ArrayQuestions.makePositive(input);
        assertArrayEquals(expected, input);
    }
}
