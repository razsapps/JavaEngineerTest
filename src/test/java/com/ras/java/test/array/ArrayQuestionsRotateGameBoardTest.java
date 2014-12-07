package com.ras.java.test.array;

import com.ras.java.test.ArrayQuestions;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by RAS on 11/23/2014.
 */
public class ArrayQuestionsRotateGameBoardTest {
    private char[][] initializeGameBoard(int size) {
        char[][] board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    private char[][] getDefaultGameBoard() {
        char[][] board = initializeGameBoard(3);
        board[1][0] = 'B';
        board[1][1] = 'Y';
        board[1][2] = 'E';
        board[0][2] = 'H';
        board[2][2] = 'Y';
        return board;
    }

    @Test
    public void testSingleRotation() {
        char[][] board = getDefaultGameBoard();
        char[][] expected = initializeGameBoard(3);
        expected[0][1] = 'B';
        expected[1][1] = 'Y';
        expected[2][1] = 'E';
        expected[2][2] = 'H';
        expected[2][0] = 'Y';
        ArrayQuestions.rotateGameBoard(board, 1);
        assertArrayEquals("Failed for a single rotation", expected, board);
    }

    @Test
    public void testDoubleRotation() {
        char[][] board = getDefaultGameBoard();
        char[][] expected = initializeGameBoard(3);
        expected[1][2] = 'B';
        expected[1][1] = 'Y';
        expected[1][0] = 'E';
        expected[2][0] = 'H';
        expected[0][0] = 'Y';
        ArrayQuestions.rotateGameBoard(board, 2);
        assertArrayEquals("Failed for a double rotation", expected, board);
    }

    @Test
    public void testTripleRotation() {
        char[][] board = getDefaultGameBoard();
        char[][] expected = initializeGameBoard(3);
        expected[2][1] = 'B';
        expected[1][1] = 'Y';
        expected[0][1] = 'E';
        expected[0][0] = 'H';
        expected[0][2] = 'Y';
        ArrayQuestions.rotateGameBoard(board, 3);
        assertArrayEquals("Failed for a triple rotation", expected, board);
    }

    @Test
    public void testQuadrupleRotation() {
        char[][] board = getDefaultGameBoard();
        char[][] expected = getDefaultGameBoard();
        ArrayQuestions.rotateGameBoard(board, 4);
        assertArrayEquals("Failed for a quadruple rotation", expected, board);
    }
}
