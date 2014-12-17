package com.ras.java.test.linkedlist;

import com.ras.java.test.core.SimpleListNode;
import org.junit.Test;

import javax.xml.soap.Node;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by RAS on 11/23/2014.
 */
public class LinkedListUtil {
    public static SimpleListNode createListFromString(String s) {
        String[] parts = s.split("->");

        SimpleListNode head = new SimpleListNode(-1);
        SimpleListNode next = head;
        for (String part: parts) {
            next = next.setNext(Integer.parseInt(part.trim()));
        }
        return head.getNext();
    }

    public static SimpleListNode createListForEachNumber(int size) {
        if (size < 1)
            throw new IllegalArgumentException("Please provide a valid size");
        SimpleListNode node = new SimpleListNode(1);
        SimpleListNode end = node;

        for (int i = 2; i <= size; i++)
            end = end.setNext(i);
        return node;
    }
}
