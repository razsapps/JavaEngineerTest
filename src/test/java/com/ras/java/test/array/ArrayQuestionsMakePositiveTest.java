package com.ras.java.test.array;

import com.ras.java.test.ArrayQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by RAS on 12/7/2014.
 */
public class ArrayQuestionsMakePositiveTest {
    @Test
    public void testExample() {
        String[] input = new String[]{"This", "project", "sucks", "Rich", "sucks", null, null};
        String[] expected = new String[]{"This", "project", "is", "awesome", "Rich", "is", "awesome"};
        ArrayQuestions.makePositive(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testValid() {
        String[] input = new String[]{"This", "question", "sucks", null};
        String[] expected = new String[]{"This", "question", "is", "awesome"};
        ArrayQuestions.makePositive(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testDefensiveNull() {
        try {
             ArrayQuestions.makePositive(null);
        }
        catch (IllegalArgumentException e) {
            //This passes if they throw an error on a bad input
        }
    }

    @Test
    public void testDefensiveEmpty() {
        try {
            String[] input = new String[0];
            ArrayQuestions.makePositive(null);
        }
        catch (IllegalArgumentException e) {
            //This passes if they throw an error on a bad input
        }
    }

    /**
     * @param size base size of the array that is filled with random strings
     * @param nullPadding how much space to leave for padding
     * @returnan array that is size + null padding. 0 to size is filled with a string, the rest is null
     */
    private String[] generateRandomInput(int size, int nullPadding) {
        String[] array = new String[size + nullPadding];
        for (int i = 0; i < size; i++)
            array[i] = "random" + i;
        return array;
    }

    @Test
    public void testEfficiencyNoNulls() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] questionInput = generateRandomInput(2500000, 0);
        String[] testInput = Arrays.copyOf(questionInput, questionInput.length);
        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("makePositive")
                .setParameterTypes(String[].class)
                .setQuestionInput(questionInput)
                .setInputs(testInput)
                .testEfficient();
    }

    @Test
    public void testEfficiencyShortCircuit() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] questionInput = generateRandomInput(2500000, 1);
        questionInput[questionInput.length - 2] = "sucks";
        String[] testInput = Arrays.copyOf(questionInput, questionInput.length);
        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("makePositive")
                .setParameterTypes(String[].class)
                .setQuestionInput(questionInput)
                .setInputs(testInput)
                .testEfficient();
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int size = 10000000;
        int nullPadding = 5;
        String[] questionInput = generateRandomInput(size, nullPadding);
        Random r = new Random();
        //Add in the word sucks nullPadding times
        for (int i = 0; i < nullPadding; i++) {
            int index = r.nextInt(size);
            String value = questionInput[index];
            //If the value has already changed let's just try again
            if ("sucks".equals(value))
                i--;
            else
                questionInput[index] = "sucks";
        }
        String[] testInput = Arrays.copyOf(questionInput, questionInput.length);
        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("makePositive")
                .setParameterTypes(String[].class)
                .setQuestionInput(questionInput)
                .setTestInput(testInput)
                .testEfficient();
    }

    public static void makePositive(String[] words) {
        if (words == null)
            throw new IllegalArgumentException("Null is not a valid input.");
        else if (words.length == 0)
            return;

        int firstWord = -1;
        //Determine the first non null
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i] != null) {
                firstWord = i;
                break;
            }
        }

        //Short circuit
        if (firstWord == words.length - 1)
            return;

        //Assign the words
        int assignIndex = words.length - 1;
        for (int i = firstWord; i >= 0; i--) {
            String word = words[i];
            if ("sucks".equalsIgnoreCase(word)) {
                words[assignIndex] = "awesome";
                assignIndex--;
                words[assignIndex] = "is";
                assignIndex--;
            }
            else {
                words[assignIndex] = word;
                assignIndex--;
            }

            //Short circuit
            if (assignIndex == i - 1)
                return;
        }
    }
}
