package com.ras.java.test.linkedlist;

import com.ras.java.test.LinkedListQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import com.ras.java.test.core.SimpleListNode;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class LinkedListQuestionsBreakTheLoopTest {
    @Test
    public void testExample() {
        SimpleListNode head = LinkedListUtil.createListForEachNumber(5);
        SimpleListNode nodeThree = head.getNext().getNext();
        SimpleListNode nodeFive = nodeThree.getNext().getNext();
        nodeFive.setNext(nodeThree);
        LinkedListQuestions.breakTheLoop(head);
        assertTrue("You failed to break the loop or you broke it at the wrong spot", LinkedListUtil.checkSize(head, 5));
        assertEquals("1 -> 2 -> 3 -> 4 -> 5", head.toString());
    }

    @Test
    public void testLoopIsFirstNode() {
        SimpleListNode head = LinkedListUtil.createListForEachNumber(3);
        SimpleListNode nodeFive = head.getNext().getNext();
        nodeFive.setNext(head);
        LinkedListQuestions.breakTheLoop(head);
        assertTrue("You failed to break the loop or you broke it at the wrong spot", LinkedListUtil.checkSize(head, 3));
        assertEquals("1 -> 2 -> 3", head.toString());
    }

    @Test
    public void testSingleNode() {
        SimpleListNode head = new SimpleListNode(1);
        head.setNext(head);
        LinkedListQuestions.breakTheLoop(head);
        assertTrue("You failed to break the loop or you broke it at the wrong spot", LinkedListUtil.checkSize(head, 1));
        assertEquals("1", head.toString());
    }

    @Test
    public void testDefensiveNull() {
        try {
            LinkedListQuestions.breakTheLoop(null);
            //No error should probably be acceptable as well
            return;
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    @Test
    public void testDefensiveNoLoop() {
        SimpleListNode head = LinkedListUtil.createListForEachNumber(5);

        try {
            LinkedListQuestions.breakTheLoop(head);
            //If there is no specific error thrown then the list should not be modified
            assertEquals("1 -> 2 -> 3 -> 4 -> 5", head.toString());
        }
        catch (IllegalArgumentException e) {
            //This is acceptable let's pass
        }
    }

    private SimpleListNode generateEfficiencyList(int size, int loopValue) {
        SimpleListNode loopStartNode = null;
        SimpleListNode node = new SimpleListNode(1);
        SimpleListNode end = node;

        if (loopValue == 1)
            loopStartNode = node;

        for (int i = 2; i <= size; i++) {
            end = end.setNext(i);
            if (i == loopValue)
                loopStartNode = end;
        }

        end.setNext(loopStartNode);
        return node;
    }

    @Test
    public void testEfficiencyLoopStartIsFirstNode() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final int size = 7500000;
        final int loopValue = 1;
        SimpleListNode testNode = generateEfficiencyList(size, loopValue);
        SimpleListNode questionNode = generateEfficiencyList(size, loopValue);
        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(LinkedListQuestions.class)
                .setMethodName("breakTheLoop")
                .setParameterTypes(SimpleListNode.class)
                .setTestInput(testNode)
                .setQuestionInput(questionNode)
                .testEfficient();
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final int size = 7500000;
        final int loopValue = size / 2;
        SimpleListNode testNode = generateEfficiencyList(size, loopValue);
        SimpleListNode questionNode = generateEfficiencyList(size, loopValue);
        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(LinkedListQuestions.class)
                .setMethodName("breakTheLoop")
                .setParameterTypes(SimpleListNode.class)
                .setTestInput(testNode)
                .setQuestionInput(questionNode)
                .testEfficient();
    }

    public static void breakTheLoop(SimpleListNode node) {
        if (node == null)
            throw new IllegalArgumentException("The list must not be null");

        SimpleListNode slow = node;
        SimpleListNode fast = node;

        //First see if they intersect and there is a loop
        while (fast != null && fast.getNext() != null) {
            //We want too break before assigning them for a special edge case that the first node is the loop point
            if (slow.getNext() == fast.getNext().getNext())
                break;

            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        //There is no loop.  Do not continue
        if (fast == null || fast.getNext() == null)
            return;

        //Special case check for if the first node started the loop
        if (fast.getNext().getNext() == node) {
            fast.getNext().setNext(null);
            return;
        }

        //At this point if we resent slow to the head and increment both slow and fast one node at a time
        //They would meet at the first element in the loop.  We want fast to be the last element before the loop starts
        slow = node;
        fast = fast.getNext().getNext();
        while (slow.getNext() != fast.getNext()) {
            slow = slow.getNext();
            fast = fast.getNext();
        }

        fast.setNext(null);
    }
}
