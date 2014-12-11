package com.ras.java.test.array;

import com.ras.java.test.ArrayQuestions;
import com.ras.java.test.core.EfficiencyTestExecutor;
import com.ras.java.test.core.NotAnsweredException;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

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
        try {
            ArrayQuestions.rotateGameBoard(board, 4);
            assertArrayEquals("Failed for a quadruple rotation", expected, board);
        }
        catch(IllegalArgumentException e) {
            //We'll accept this if they say 1-3 only
        }
    }

    @Test
    public void testNegativeRotation() {
        char[][] board = getDefaultGameBoard();
        char[][] expected = initializeGameBoard(3);
        expected[2][1] = 'B';
        expected[1][1] = 'Y';
        expected[0][1] = 'E';
        expected[0][0] = 'H';
        expected[0][2] = 'Y';

        try {
            ArrayQuestions.rotateGameBoard(board, -1);
        }
        catch (IllegalArgumentException e) {
            //This passes if they throw an error. They could be ensuring the rotation is positive
            return;
        }

        //If the problem processes then they should actually handle the fact the rotation is negative
        assertArrayEquals("Failed for a negative rotation", expected, board);
    }

    @Test
    public void testDefensiveNull() {
        try {
            ArrayQuestions.rotateGameBoard(null, 1);
        }
        catch (IllegalArgumentException e) {
            //This passes if they throw an error on a bad input
        }
    }

    @Test
    public void testDefensiveEmpty() {
        char[][] board = new char[0][0];
        try {
            ArrayQuestions.rotateGameBoard(board, 1);
        }
        catch (IllegalArgumentException e) {
            //This passes if they throw an error on a bad input
            return;
        }
        assertArrayEquals("Failed for an empty board", new char[0][0], board);

    }

    private static char[][] generateLargeBoard(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 'A';
            }
        }
        return board;
    }

    @Test
    public void testEfficiency() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        char[][] board = generateLargeBoard(15000);

        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("rotateGameBoard")
                .setParameterTypes(char[][].class, int.class)
                .setInputs(board, 1)
                .testEfficient();
    }

    @Test
    public void testEfficiencyTripleRotation() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        char[][] board = generateLargeBoard(15000);

        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("rotateGameBoard")
                .setParameterTypes(char[][].class, int.class)
                .setInputs(board, 3)
                .testEfficient();
    }

    @Test
    public void testEfficiencyLargeRotationCount() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        char[][] board = generateLargeBoard(10000);

        new EfficiencyTestExecutor()
                .setTestClass(this.getClass())
                .setQuestionClass(ArrayQuestions.class)
                .setMethodName("rotateGameBoard")
                .setParameterTypes(char[][].class, int.class)
                .setInputs(board, 41)
                .testEfficient();
    }

    /**
     * The purpose of this method is to return the index of a corner.
     *
     * In a 2x2 matrix this means
     *  corner 0 is 0,0
     *  corner 1 is 0,1
     *  corner 2 is 1,1
     *  corner 3 is 1,0
     *
     *  This will help us determine the index we need to work with when we take into account the corner we want
     *  and how many rotations we went through.
     *
     * @param corner the corner number of 0-3
     * @param i - The current index of i in the ixj matrix
     * @param j - The current index of j in the ixj matrix
     * @param n - The n size of the nxn matrix
     * @return an array that is int[i,j] of the corner number
     */
    private static int[] getCornerPoint(int corner, int i, int j, int n) {
        //There are only 4 corners
        corner = corner % 4;

        switch(corner) {
            case 0:
                return new int[]{i,j};
            case 1:
                return new int[]{n - 1 - j, i};
            case 2:
                return new int[]{n - 1 - i, n - 1 - j};
            case 3:
                return new int[]{j, n - 1 - i};
            default:
                throw new IllegalArgumentException("There are only 4 corners possible.");
        }
    }

    public static void rotateGameBoard(char[][] board, int rotationCount) {
        if (board == null)
            throw new IllegalArgumentException("You must provide a board.");
        else if (board.length == 0)
            return;

        //There are only 4 possible layouts
        rotationCount = rotationCount % 4;
        //Nothing to rotate here
        if (rotationCount == 0)
            return;
        //Only 4 possible rotations
        else if (rotationCount < 0)
            rotationCount = 4 - rotationCount;

        int n = board.length;

        //Since we are rotating the elements we only need to process half the i/js because the other half
        //is processed during the rotation
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                //Store the corners temporarily
                //This could theoretically store the whole board if it was 2x2, but we never use more than 4 values
                //Even if the board is 100x100 so this meets the criteria
                char[] cornerValues = new char[4];
                for (int corner = 0; corner < cornerValues.length; corner++) {
                    int[] point = getCornerPoint(corner, i, j, n);
                    cornerValues[corner] = board[point[0]][point[1]];
                }

                //Assign the corners their new values
                for (int corner = 0; corner < cornerValues.length; corner++) {
                    int[] point = getCornerPoint(corner + (4 - rotationCount), i, j, n);
                    board[point[0]][point[1]] = cornerValues[corner];
                }
            }
        }
    }
}
