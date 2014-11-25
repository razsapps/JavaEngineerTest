package com.ras.java.test.core;

/**
 * Created by RAS on 11/24/2014.
 */
public class SimpleTreeNode {
    private SimpleTreeNode m_leftNode = null;
    private SimpleTreeNode m_rightNode = null;
    private int m_value;

    public SimpleTreeNode() {

    }

    public SimpleTreeNode getLeftNode() {
        return m_leftNode;
    }

    public SimpleTreeNode setLeftNode(SimpleTreeNode leftNode) {
        m_leftNode = leftNode;
        return this;
    }

    public SimpleTreeNode getRightNode() {
        return m_rightNode;
    }

    public SimpleTreeNode setRightNode(SimpleTreeNode rightNode) {
        m_rightNode = rightNode;
        return this;
    }

    public int getValue() {
        return m_value;
    }

    public SimpleTreeNode setValue(int value) {
        m_value = value;
        return this;
    }
}
