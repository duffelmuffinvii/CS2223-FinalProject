import java.util.ArrayList;

public class Horspool {

    public static int getAlphaSize(String str) {
        int highest = 0;
        for (char c : str.toCharArray()) {
            if (c > highest) {
                highest = c;
            }
        }

        return highest;
    }

    public static int[] preprocess(String pattern, String fullStr) {
        int[] T = new int[getAlphaSize(fullStr)+1];
        for (int i = 0; i < T.length; i++) {
            T[i] = pattern.length();
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            T[pattern.charAt(i)] = pattern.length() - 1 - i;
        }

        return T;
    }

    public static boolean compareToLength(String str1, String str2, int len) {
        int i = len-1;
        if (str1.length() < len || str2.length() < len)
            return false;
        while (str1.charAt(i) == str2.charAt(i)) {
            if (i == 0) {
                return true;
            }
            i--;
        }
        return false;
    }

    /**
     *Calling method for this Horspool's search implementation (This version returns FIRST match)
     * Uses Horspool's algorithm to search for string in given text
     * @param subStr String to search for
     * @param str Input text to run the search on
     * @return The first match
     */
    public static int search(String subStr, String str) {
        int[] T = preprocess(subStr, str);
        int skip = 0;

        while (str.length() - skip >= subStr.length()) {
            if (compareToLength(str.substring(skip), subStr, subStr.length()))
                return skip;
            skip += T[str.charAt(skip + subStr.length() - 1)];
        }

        //Returns -1 if substring is not found
        return -1;
    }

    /**
     * Calling method for this Horspool's search implementation (This version returns ALL matches)
     * Uses Horspool's algorithm to search for string in given text
     * @param subStr String to search for
     * @param str Input text to run the search on
     * @return A List of all matching string's indexes
     */
    public static ArrayList<Integer> searchAll(String subStr, String str) {
        int[] T = preprocess(subStr, str);
        ArrayList<Integer> matches = new ArrayList<>();

        int skip = 0;

        while (str.length() - skip >= subStr.length()) {
            if (compareToLength(str.substring(skip), subStr, subStr.length()))
                matches.add(skip);
            skip += T[str.charAt(skip + subStr.length() - 1)];
        }

        return matches;
    }

    public static String highlight(String subStr, String str) {
        ArrayList<Integer> locations = searchAll(subStr, str);
        String highlighted = str.toLowerCase();

        for (int n : locations) {
            highlighted = highlighted.substring(0, n) + subStr.toUpperCase() + highlighted.substring(n + subStr.length(), str.length() - 1);
        }

        return highlighted;
    }

}
