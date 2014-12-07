package com.ras.java.test.array;

import com.ras.java.test.ArrayQuestions;
import com.ras.java.test.core.NotAnsweredException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by RAS on 12/7/2014.
 */
public class ArrayQuestionsIsPermutationTest {
    @Test
    public void testExample() {
        assertTrue("Failed for input 'aBc12' and 'Bc1a2'", ArrayQuestions.isPermutation("aBc12".toCharArray(), "Bc1a2".toCharArray()));
    }

    @Test
    public void testValid() {
        assertTrue("Failed for input 'You are Correct!' and 'CYo !areuec rrt'", ArrayQuestions.isPermutation("You are Correct!".toCharArray(), "CYo !areuec rrt".toCharArray()));
    }

    @Test
    public void testInvalid() {
        assertFalse("Failed for invalid input 'You are' 'Wrong'", ArrayQuestions.isPermutation("You are".toCharArray(), "Wrong".toCharArray()));
    }
}
