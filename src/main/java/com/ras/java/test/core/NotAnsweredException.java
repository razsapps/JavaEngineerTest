package com.ras.java.test.core;

/**
 * Created by RAS on 11/23/2014.
 */
public class NotAnsweredException extends UnsupportedOperationException {
    public NotAnsweredException() {
        super("The question has not been answered yet.");
    }
}
