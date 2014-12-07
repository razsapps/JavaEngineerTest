package com.ras.java.test.array;

import com.ras.java.test.ArrayQuestions;
import org.junit.Test;

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
}
