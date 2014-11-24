package com.ras.java.test.core;

/**
 * Created by RAS on 11/23/2014.
 */
public class SimpleListNode {
    private SimpleListNode m_next = null;
    private int m_value;

    public SimpleListNode(int value) {
        m_value = value;
    }

    /**
     * Sets the next node for this specific Node
     * @param next the next list node
     * @return the next list node
     */
    public SimpleListNode setNext(SimpleListNode next) {
        m_next = next;
        return next;
    }

    /**
     * Auto creates a SimpleListNode with the value provided and set it as the next node in the list
     */
    public SimpleListNode setNext(int value) {
        return setNext(new SimpleListNode(value));
    }

    /**
     * @return The next SimpleListNode after this one
     */
    public SimpleListNode getNext() {
        return m_next;
    }

    public SimpleListNode setValue(int value) {
        m_value = value;
        return this;
    }

    public int getValue() {
        return m_value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getValue());

        SimpleListNode next = getNext();
        while (next != null) {
            sb.append(" -> ").append(next.getValue());
            next = next.getNext();
        }
        return sb.toString();
    }
}
