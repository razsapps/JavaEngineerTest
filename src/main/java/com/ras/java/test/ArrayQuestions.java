package com.ras.java.test;

import com.ras.java.test.core.NotAnsweredException;

/**
 * This test is to attempt to test your ability to work with arrays
 */
public class ArrayQuestions {
    /**
     * Given a character array determine if that array is a palindrome where case does not matter and neither does spaces.
     *
     * Details:
     * A capital 'A' and a lower case 'a' are considered the same
     * Spaces can be in the word, but shouldn't be taken into account
     *
     * Restrictions:
     * Do not use any other data structures
     * Do not use the String API
     *
     * Example:
     * Input: ['N','o','e','l',' ','s','e','e','s',' ','L','e','o','n']
     * return: true
     *
     * @param input your given character array
     * @return if input is a palindrome
     */
    public static boolean isPalindrome(char[] input) {
        throw new NotAnsweredException();
    }

    /**
     * Given a two char arrays determine if they are permutations of each other
     *
     * Restrictions:
     * Do not use the String API
     *
     * Details:
     * An upper case is not equivalent to a lower case ['A','B'] is not a permutation of ['B','a']
     * The characters are in ASCII format
     *
     * Example:
     * Input:
     *      one: ['a','B','c','1','2']
     *      two: ['B','c','1','a','2']
     * return: true
     *
     * @param one your given first char array
     * @param two your given second char array
     * @return if one and two are permutations of each other
     */
    public static boolean isPermutation(char[] one, char[] two) {
        throw new NotAnsweredException();
    }

    /**
     * Given an array of words replace the word "sucks" with two words "is" "awesome" in place of the array
     *
     * Details:
     * The array will be initialized with null padding on the end for all the words after "sucks" is replaced.
     *      See example for details
     *
     * Restrictions:
     * Do not use any other data structures. You should not temporarily store any of the words
     *
     * Example:
     * Input: ["This","project","sucks","Rich","sucks",null,null]
     * Outcome:
     *      words will now be set to this: ["This","project","is","awesome","Rich","is","awesome"]
     *
     * @param words An array of words which will need the word "sucks" replaced with the two words "is" "awesome"
     */
    public static void makePositive(String[] words) {
        throw new NotAnsweredException();
    }

    /**
     * You are playing on square gameboard that holds characters. You will like to be able to rotate the gameboard
     * clockwise so players can read the board better.  You must be able to rotate the location of characters in
     * place on the gameboard for the amount of times specified.
     *
     * Restrictions:
     * Do not use any other data structures
     *
     * Example:
     * Input: rotationCount: 1
     *      board:
     *      [' ',' ','H']
     *      ['B','Y','E']
     *      [' ',' ','Y']
     *
     * Outcome: board should contain the value below
     *      [' ','B',' ']
     *      [' ','Y',' ']
     *      ['Y','E','H']
     *
     * Input: rotationCount: 2
     *      board: Same as the input provided in the previous example
     *      [' ',' ','Y']
     *      ['B','Y','E']
     *      [' ',' ','H']
     *
     * @param board The board that needs to be rotated in place
     * @param rotationCount The amount of times to rotate the board
     */
    public static void rotateGameBoard(char[][] board, int rotationCount) {
        throw new NotAnsweredException();
    }
}
