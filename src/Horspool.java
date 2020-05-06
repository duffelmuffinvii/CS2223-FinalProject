import java.util.ArrayList;

public class Horspool {

    public static int[] preprocess(String pattern) {
        int[] T = new int[256];
        for (int i = 0; i < 256; i++) {
            T[i] = pattern.length();
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            T[pattern.charAt(i)] = pattern.length() - 1 - i;
        }

        return T;
    }

    public static boolean compareToLength(String str1, String str2, int len) {
        int i = len-1;
        while (str1.charAt(i) == str2.charAt(i)) {
            if (i == 0) {
                return true;
            }
            i--;
        }
        return false;
    }

    public static int search(String subStr, String str) {
        int[] T = preprocess(subStr);
        int skip = 0;

        while (str.length() - skip >= subStr.length()) {
            if (compareToLength(str.substring(skip, str.length()-1), subStr, subStr.length()))
                return skip;
            skip += T[str.charAt(skip + subStr.length() - 1)];
        }

        //Returns -1 if substring is not found
        return -1;
    }

    public static ArrayList<Integer> searchAll(String subStr, String str) {
        int[] T = preprocess(subStr);
        ArrayList<Integer> matches = new ArrayList<>();

        int skip = 0;

        while (str.length() - skip >= subStr.length()) {
            if (compareToLength(str.substring(skip, str.length()-1), subStr, subStr.length()))
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
