package ctci.solutions.aritra;

/**
 * Cracking the Coding Interview – Arrays & Strings
 *
 * Problem:
 * Implement an algorithm to determine if a string has all unique characters.
 *
 * Assumptions:
 * - The string uses the standard ASCII character set (128 characters).
 * - Case-sensitive comparison.
 *
 * Constraint Discussion:
 * - This solution uses an additional data structure (boolean array).
 * - A follow-up solution without extra space can be done using sorting or
 *   bit manipulation.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) — fixed-size array (128)
 *
 * @author ARITCHAT
 */
public class Approach1 {

    public static void main(String[] args) {
        String test1 = "animal";
        String test2 = "rock";

        System.out.println("All characters unique in test1? " + isUniqueChars(test1));
        System.out.println("All characters unique in test2? " + isUniqueChars(test2));
    }

    /**
     * Checks whether a string contains all unique characters.
     *
     * Logic:
     * - If string length exceeds the total number of ASCII characters (128),
     *   duplicates are guaranteed.
     * - Use a boolean array to track character occurrence.
     *
     * @param str input string
     * @return true if all characters are unique, false otherwise
     */
    public static boolean isUniqueChars(String str) {

        /*
         * Optimization:
         * If the string length is greater than the total number of unique ASCII
         * characters, it cannot have all unique characters.
         */
        if (str.length() > 128) {
            return false;
        }

        // Boolean array to track whether a character has appeared before
        boolean[] charSet = new boolean[128];

        // Iterate through each character in the string
        for (int i = 0; i < str.length(); i++) {

            // Get ASCII value of the current character
            int val = str.charAt(i);

            // If character already encountered, string is not unique
            if (charSet[val]) {
                return false;
            }

            // Mark character as seen
            charSet[val] = true;
        }

        // All characters were unique
        return true;
    }
}
