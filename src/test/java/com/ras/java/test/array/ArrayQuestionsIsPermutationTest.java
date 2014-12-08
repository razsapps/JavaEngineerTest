package com.ras.java.test.array;

import com.ras.java.test.ArrayQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

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
        assertTrue("Failed for input 'You are Correct!' and 'CYo !areuec rrt'", ArrayQuestions.isPermutation("You are Correct!".toCharArray(), "CYo !areuec rrot".toCharArray()));
    }

    @Test
    public void testInvalid() {
        assertFalse("Failed for invalid input 'You are' 'Wrong'", ArrayQuestions.isPermutation("You are".toCharArray(), "Wrong".toCharArray()));
    }

    @Test
    public void testDefensiveNull() {
        try {
            //We'll take false here as an acceptable answer
            assertFalse("Should handle null correctly", ArrayQuestions.isPermutation(null, null));
        }
        catch (IllegalArgumentException e) {
            //This passes if they throw an error on a bad input
        }
    }

    private static char[] generateRandomTestArray(int size) {
        char[] array = new char[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            char c = (char)random.nextInt(128);
            array[i] = c;
        }
        return array;
    }

    @Test
    public void testEfficiencyShortCircuitDifferentSizedInputs() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //Generate a random character array that is long
        char[] one = generateRandomTestArray(100000000);
        char[] two = generateRandomTestArray(1);

        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("isPermutation")
                .setParameterTypes(char[].class, char[].class)
                .setInputs(one, two)
                .testEfficient();
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //Generate a random character array that is long
        char[] one = generateRandomTestArray(100000000);

        char[] two = Arrays.copyOf(one, one.length);
        //Shuffle values
        Random r = new Random();
        for (int i = 0; i < two.length; i++) {
            char temp = two[i];
            int index = r.nextInt(two.length);
            two[i] = two[index];
            two[index] = temp;
        }

        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("isPermutation")
                .setParameterTypes(char[].class, char[].class)
                .setInputs(one, two)
                .testEfficient();
    }

    public static boolean isPermutation(char[] one, char[] two) {
        if (one == null || two == null)
            throw new IllegalArgumentException("The inputs cannot be null");

        //Quick test
        if (one.length != two.length)
            return false;

        //ASCII uses 128 bits to represent a character
        int[] characters = new int[128];

        //Add a count for each character in the array
        for (char c: one) {
            int index = (int) c;
            //Safety check
            if (index >= 128)
                throw new IllegalArgumentException("A non ASCII character was passed in input one.");
            characters[index]++;
        }

        //Subtract for each character in the array
        for (char c: two) {
            int index = (int) c;
            //Safety check
            if (index >= 128)
                throw new IllegalArgumentException("A non ASCII character was passed in input two.");
            characters[index]--;

            //No need to continue anymore
            if (characters[index] < 0)
                return false;
        }

        //If both arrays are the same length and when subtracting the values we never dropped below 0 that means
        //All values are at 0 which means they are permutations so let's return true
        return true;
    }
}
