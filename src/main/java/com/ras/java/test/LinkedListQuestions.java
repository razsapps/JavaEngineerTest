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
     * return: 1 -> 5 -> 7 -> 21 -> 23 -> 42
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
     * You cannot use any other data structures including a new Linked List
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
}
