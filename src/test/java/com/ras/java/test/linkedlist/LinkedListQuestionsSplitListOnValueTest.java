package com.ras.java.test.linkedlist;

import com.ras.java.test.LinkedListQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import com.ras.java.test.core.SimpleListNode;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by RAS on 12/10/2014.
 */
public class LinkedListQuestionsSplitListOnValueTest {
    @Test
    public void testExample() {
        SimpleListNode node = LinkedListUtil.createListFromString("21 -> 5 -> 7 -> 42 -> 23 -> 1");
        SimpleListNode actual = LinkedListQuestions.splitListOnValue(node, 21);
        assertEquals("5 -> 7 -> 1 -> 21 -> 42 -> 23", actual.toString());
    }

    @Test
    public void testSplitValueIsLowest() {
        SimpleListNode node = LinkedListUtil.createListFromString("27 -> 15 -> 19 -> 55 -> 40 -> 1");
        SimpleListNode actual = LinkedListQuestions.splitListOnValue(node, 1);
        assertEquals("1 -> 27 -> 15 -> 19 -> 55 -> 40", actual.toString());
    }

    @Test
    public void testSplitValueIsHighest() {
        SimpleListNode node = LinkedListUtil.createListFromString("27 -> 15 -> 19 -> 55 -> 40 -> 1");
        SimpleListNode actual = LinkedListQuestions.splitListOnValue(node, 55);
        assertEquals("27 -> 15 -> 19 -> 40 -> 1 -> 55", actual.toString());
    }

    @Test
    public void testDefensiveNull() {
        try {
            SimpleListNode actual = LinkedListQuestions.splitListOnValue(null, 0);
            assertNull(actual);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveSplitValueMissing() {
        SimpleListNode node = LinkedListUtil.createListFromString("21 -> 5 -> 7 -> 42 -> 23 -> 1");
        try {
            SimpleListNode actual = LinkedListQuestions.splitListOnValue(node, 99);
            //If the value isn't provided the list returned should be the same as input
            assertEquals("21 -> 5 -> 7 -> 42 -> 23 -> 1", actual.toString());
        }
        catch(IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int size = 15000000;
        SimpleListNode oneStart = new SimpleListNode(1);
        SimpleListNode oneEnd = oneStart;
        SimpleListNode twoStart = new SimpleListNode(1);
        SimpleListNode twoEnd = twoStart;

        for (int i = 2; i <= size; i++) {
            oneEnd = oneEnd.setNext(i);
            twoEnd = twoEnd.setNext(i);
        }

        int half = size/2;
        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(LinkedListQuestions.class)
                .setMethodName("splitListOnValue")
                .setParameterTypes(SimpleListNode.class, int.class)
                .setQuestionInput(oneStart, half)
                .setTestInput(twoStart, half)
                .testEfficient();
    }

    public static SimpleListNode splitListOnValue(SimpleListNode node, int splitValue) {
        if (node == null)
            throw new IllegalArgumentException("Please provide a head node to the list.");

        SimpleListNode lowStart = null;
        SimpleListNode lowEnd = null;
        SimpleListNode highStart = null;
        SimpleListNode highEnd = null;
        SimpleListNode value = null;

        //Loop through the list
        while (node != null) {
            //If this node comes before, after, or is the same as the splitValue
            int compare = node.getValue() - splitValue;

            if (compare == 0)
                value = node;
            else if (compare < 0) {
                if (lowStart == null)
                    lowStart = node;
                else
                    lowEnd.setNext(node);
                lowEnd = node;
            }
            else {
                if (highStart == null)
                    highStart = node;
                else
                    highEnd.setNext(node);
                highEnd = node;
            }

            node = node.getNext();
        }

        //Did not find the value
        if (value == null)
            throw new IllegalArgumentException("The split value was not found.");

        //There are values lower than splitValue so add the value node at the end of the low end of the list
        if (lowStart != null)
            lowEnd.setNext(value);
        //splitValue is the lowest value node so start the list with it
        else
            lowStart = value;

        //There are values higher than splitValue so add the higher values to after the value node
        if (highStart != null)
            value.setNext(highStart);
        //splitValue is the highest value node so end the list with it
        else
            highEnd = value;

        //Set the last value to null to be sure the list is terminated
        highEnd.setNext(null);

        return lowStart;
    }
}
