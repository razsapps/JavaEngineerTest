package com.ras.java.test;

import org.junit.Test;

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
}
