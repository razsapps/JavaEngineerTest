package com.ras.java.test;

import com.ras.java.test.core.NotAnsweredException;
import com.ras.java.test.core.SimpleListNode;

/**
 * Created by RAS on 11/23/2014.
 */
public class LinkedListQuestions {
    /**
     * Given the head node in a linked list split the list so all values less than splitValue comes before the node
     * with that value and all values higher comes after the node with that value.
     *
     * Details:
     * The values less than splitValue should appear before splitValue in the order that they were provided in the list
     * The values greater than splitValue should appear after splitValue in the order that they were provided in the list
     * splitValue is unique and will not be repeated
     *
     * Example:
     * Input:
     *      node: 21 -> 5 -> 7 -> 42 -> 23 -> 1
     *      splitValue: 21
     * return: 5 -> 7 -> 1 -> 21 -> 42 -> 23
     *
     * @param node the head node in a linked list
     * @param splitValue the value that we want to split the list on
     * @return A list that has all values less than splitValue before splitValue and all values greater than it after it
     */
    public static SimpleListNode splitListOnValue(SimpleListNode node, int splitValue) {
        throw new NotAnsweredException();
    }

    /**
     * Given the head node in a linked list return the sublist provided by the start and end indexes
     *
     * Restrictions:
     * You cannot use any other data structures including a new Linked List (you must modify the provided nodes)
     *
     * Example:
     * node: 1 -> 2 -> 3-> 4 -> 5
     * start: 1
     * end: 3
     * return: 2 -> 3 -> 4
     *
     * @param node the head node in a linked list
     * @param start the index for the first element in the sub list (inclusive)
     * @param end the index for the last element in the sub list (inclusive)
     * @return The sublist
     */
    public static SimpleListNode subList(SimpleListNode node, int start, int end) {
        throw new NotAnsweredException();
    }

    /**
     * Given a Linked List determine if that Linked List is a palindrome.
     *
     * Details:
     * Speed is more important than memory usage
     *
     * Example:
     * node: 1 -> 2 -> 3 -> 4 -> 3 -> 2 -> 1
     * return: true
     *
     * @param node the head node in a linked list
     * @return if the linked list is a palindrome
     */
    public static boolean isPalindrome(SimpleListNode node) {
        throw new NotAnsweredException();
    }

    /**
     * Move every n amount of items from the end of the list to the beginning of the list
     *
     * Restrictions:
     * You cannot use any other data structures including a new Linked List (you must modify the provided nodes)
     *
     * Example:
     * node: 1 -> 2 -> 3 -> 4 -> 5
     * n: 2
     * return:
     * The head node should be 4 with the following list 4 -> 5 -> 1 -> 2 -> 3
     *
     * @param node the head node in a linked list
     * @param n The amount of elements from the end to move to the front
     * @return The head node of the list now that it has been modified
     */
    public static SimpleListNode moveFromTheEndToTheFront(SimpleListNode node, int n) {
        throw new NotAnsweredException();
    }

    /**
     * There is a list that is in a loop.  Find the loop and break it.
     *
     * Restrictions:
     * You cannot use any other data structures including a new Linked List (you must modify the provided nodes)
     *
     * Example:
     * node: 1 -> 2 -> 3 -> 4 -> 5 -> 3 (This is the same exact 3 node as earlier. It will keep repeating 3,4,5)
     * Output:
     * The loop should be broken and the list should be 1 -> 2 -> 3 -> 4 -> 5
     *
     * @param node the head node in a linked list
     */
    public static void breakTheLoop(SimpleListNode node) {
        throw new NotAnsweredException();
    }
}
