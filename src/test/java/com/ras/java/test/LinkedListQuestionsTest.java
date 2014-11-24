package com.ras.java.test;

import com.ras.java.test.core.SimpleListNode;
import org.junit.Test;

import javax.xml.soap.Node;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by RAS on 11/23/2014.
 */
public class LinkedListQuestionsTest {

    private static SimpleListNode createListFromString(String s) {
        String[] parts = s.split("->");

        SimpleListNode head = new SimpleListNode(-1);
        SimpleListNode next = head;
        for (String part: parts) {
            next = next.setNext(Integer.parseInt(part.trim()));
        }
        return head.getNext();
    }
}
