package com.ras.java.test.linkedlist;

import com.ras.java.test.LinkedListQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import com.ras.java.test.core.SimpleListNode;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by RAS on 12/16/2014.
 */
public class LinkedListQuestionsMoveFromTheEndToTheFrontTest {
    @Test
    public void testExample() {
        SimpleListNode node = LinkedListUtil.createListForEachNumber(5);
        SimpleListNode actual = LinkedListQuestions.moveFromTheEndToTheFront(node, 2);
        assertEquals("4 -> 5 -> 1 -> 2 -> 3", actual.toString());
    }

    @Test
    public void testDefensiveNull() {
        try {
            SimpleListNode actual = LinkedListQuestions.moveFromTheEndToTheFront(null, 1);
            assertNull(actual);
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveNegativeN() {
        try {
            LinkedListQuestions.moveFromTheEndToTheFront(LinkedListUtil.createListForEachNumber(5), -1);
            fail("A negative value should not be valid");
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testEfficiencyN0() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {
            final int size = 15000000;
            SimpleListNode oneStart = LinkedListUtil.createListForEachNumber(size);
            SimpleListNode twoStart = LinkedListUtil.createListForEachNumber(size);

            new EfficiencyTestExecutor()
                    .setTestClass(this.getClass())
                    .setQuestionClass(LinkedListQuestions.class)
                    .setMethodName("moveFromTheEndToTheFront")
                    .setParameterTypes(SimpleListNode.class, int.class)
                    .setQuestionInput(oneStart, 0)
                    .setTestInput(twoStart, 0)
                    .testEfficient();
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {
            final int size = 15000000;
            SimpleListNode oneStart = LinkedListUtil.createListForEachNumber(size);
            SimpleListNode twoStart = LinkedListUtil.createListForEachNumber(size);
            int half = size / 2;

            new EfficiencyTestExecutor()
                    .setTestClass(this.getClass())
                    .setQuestionClass(LinkedListQuestions.class)
                    .setMethodName("moveFromTheEndToTheFront")
                    .setParameterTypes(SimpleListNode.class, int.class)
                    .setQuestionInput(oneStart, half)
                    .setTestInput(twoStart, half)
                    .testEfficient();
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    public static SimpleListNode moveFromTheEndToTheFront(SimpleListNode node, int n) {
        if (node == null)
            throw new IllegalArgumentException("The list cannot be null");
        else if (n < 0)
            throw new IllegalArgumentException("n should be positive");
        else if (n == 0)
            return node;

        SimpleListNode runner = node;
        SimpleListNode tailRunner = node;

        int i = n;
        while (i > 0 && tailRunner != null) {
            tailRunner = tailRunner.getNext();
            i--;
        }

        if (i != 0)
            throw new IllegalArgumentException("n is larger than the provided list");

        //Increment the list until runner is at list length - n - 1
        while (tailRunner.getNext() != null) {
            runner = runner.getNext();
            tailRunner = tailRunner.getNext();
        }

        SimpleListNode head = runner.getNext();
        //Set runner as the end of the list
        runner.setNext(null);
        //Move the end of the list to the front
        tailRunner.setNext(node);

        return head;
    }
}
