package com.ras.java.test.array;

import com.ras.java.test.ArrayQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by RAS on 12/7/2014.
 */
public class ArrayQuestionsIsPalindromeTest {
    @Test
    public void testExample() {
        assertTrue("Failed for input 'Noel sees Leon'", ArrayQuestions.isPalindrome("Noel sees Leon".toCharArray()));
    }

    @Test
    public void testValid() {
        assertTrue("Failed for input 'Too hot to hoot'", ArrayQuestions.isPalindrome("Too hot to hoot".toCharArray()));
    }

    @Test
    public void testInvalid() {
        assertFalse("Failed for invalid input 'Richard So'", ArrayQuestions.isPalindrome("Richard So".toCharArray()));
    }

    @Test
    public void testDefensiveNull() {
        try {
            //We'll take false here as an acceptable answer
            assertFalse("Null is not a Palindrome", ArrayQuestions.isPalindrome(null));
        }
        catch (IllegalArgumentException e) {
            //This passes if they throw an error on a bad input
        }
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //Generate a random Palindrome string that is long
        int size = 300000000;
        char[] longInput = new char[size];
        Random random = new Random();
        for (int i = 0; i < size/2; i++) {
            char c = (char)('a' + random.nextInt(26));
            longInput[i] = c;
            longInput[size - 1 - i] = c;
        }

        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("isPalindrome")
                .setParameterTypes(char[].class)
                .setInputs(longInput)
                .testEfficient();
    }

    public static boolean isPalindrome(char[] input) {
        if (input == null)
            throw new IllegalArgumentException("Null is not a valid input.");
        /*
        In these characters were to represent a string this would imply an empty string which is a palindrome
        It also remains consistent if the user provides spaces only then we'd return true below.  The question
        specified empty spaces as not legitimate characters and to skip over them
        The handling for this is debatable so feel free to handle this as you see fit.
        */
        else if (input.length == 0)
            return true;

        int start = 0;
        int end = input.length - 1;

        while (start < end) {
            char cStart = input[start];
            //Skip space and reprocess
            if (cStart == ' ') {
                start++;
                continue;
            }

            char cEnd = input[end];
            if (cEnd == ' ') {
                end--;
                continue;
            }

            if (Character.toLowerCase(cStart) != Character.toLowerCase(cEnd))
                return false;
            else {
                start++;
                end--;
            }
        }

        return true;
    }
}
