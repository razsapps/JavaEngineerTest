package com.ras.java.test.linkedlist;

import com.ras.java.test.LinkedListQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import com.ras.java.test.core.SimpleListNode;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class LinkedListQuestionsSubListTest {
    @Test
    public void testExample() {
        SimpleListNode node = LinkedListQuestions.subList(LinkedListUtil.createListFromString("1 -> 2 -> 3-> 4 -> 5"), 1, 3);
        assertEquals("2 -> 3 -> 4", node.toString());
    }

    @Test
    public void testDefensiveNull() {
        try {
            LinkedListQuestions.subList(null, 2, 4);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveNegativeStart() {
        try {
            LinkedListQuestions.subList(LinkedListUtil.createListFromString("1 -> 2 -> 3-> 4 -> 5"), -1, 4);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveNegativeEnd() {
        try {
            LinkedListQuestions.subList(LinkedListUtil.createListFromString("1 -> 2 -> 3-> 4 -> 5"), 0, -1);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveEndBeforeStart() {
        try {
            LinkedListQuestions.subList(LinkedListUtil.createListFromString("1 -> 2 -> 3-> 4 -> 5"), 3, 1);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveListShorterThanStart() {
        try {
            LinkedListQuestions.subList(LinkedListUtil.createListFromString("1 -> 2"), 2, 4);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveListShorterThanEnd() {
        try {
            LinkedListQuestions.subList(LinkedListUtil.createListFromString("1 -> 2 -> 3-> 4 -> 5"), 3, 9);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int size = 25000000;
        SimpleListNode oneStart = new SimpleListNode(1);
        SimpleListNode oneEnd = oneStart;
        SimpleListNode twoStart = new SimpleListNode(1);
        SimpleListNode twoEnd = twoStart;

        for (int i = 2; i <= size; i++) {
            oneEnd = oneEnd.setNext(i);
            twoEnd = twoEnd.setNext(i);
        }

        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(LinkedListQuestions.class)
                .setMethodName("subList")
                .setParameterTypes(SimpleListNode.class, int.class, int.class)
                .setQuestionInput(oneStart, 100, 102)
                .setTestInput(twoStart, 100, 102)
                .testEfficient();
    }

    public static SimpleListNode subList(SimpleListNode node, int start, int end) {
        if (node == null)
            throw new IllegalArgumentException("The list cannot be null");
        else if (start < 0 || end < 0)
            throw new IllegalArgumentException("The indexes must be positive");
        else if (end < start)
            throw new IllegalArgumentException("end should come after start");

        //Get the length of the sublist
        int indexDifference = end - start;

        //We want to stop 1 before
        int counter = start;
        while (counter > 0 && node != null) {
            node = node.getNext();
            counter--;
        }

        if (counter != 0 || node == null)
            throw new IllegalArgumentException("The list is not long enough");

        SimpleListNode runner = node;
        counter = indexDifference;
        while (counter > 0 && runner != null) {
            runner = runner.getNext();
            counter--;
        }

        if (counter != 0 || runner == null)
            throw new IllegalArgumentException("The list is not long enough");
        runner.setNext(null);
        return node;
    }
}
