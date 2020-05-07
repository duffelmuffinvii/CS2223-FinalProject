public abstract class BoyerMoore {
    //Preprocessing notes taken from http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Text/Docs/Boyer-Moore-notes2.pdf

    /**
     * Returns the bad character preprocessing array for Boyer Moore
     * @param pattern the pattern to preprocess
     * @param alphasize the maximum character integer representation in the pattern or text to search
     * @return an int[] where each entry is the relevant occurrence of the character at the index
     */
    public static int[] badCharacter(String pattern, int alphasize){
        int[] out = new int[alphasize+1];
        for(int a = 0; a < alphasize; a++){
            out[a] = -1;
        }
        for(int c = 0; c < pattern.length(); c++){
            out[(int) pattern.charAt(c)] = c;
        }
        return out;
    }

    /**
     * Returns the good suffix preprocessing array for Boyer Moore
     * @param pattern the pattern to preprocess
     * @return an int[] where each entry is the relevant jump for the relevant suffix
     */
    public static int[] goodSuffix(String pattern){
        int temp[] = new int[pattern.length()+1];
        int out[] = new int[pattern.length()+1];
        int i = pattern.length();
        int j = pattern.length()+1;
        temp[i] = j;
        while(i>0){
            while(j<=pattern.length() && pattern.charAt(i-1) != pattern.charAt(j-1)){
                if(out[j] == 0){out[j]=j-i;}
                j=temp[j];
            }
            i--;
            j--;
            temp[i] = j;
        }

        j = temp[0];
        for(i = 0; i<=pattern.length(); i++){
            if(out[i] == 0){
                out[i] = j;
            }
            if(i==j){
                j = temp[j];
            }
        }
        return out;
    }

    /**
     * Search for a substring in a string using Boyer Moore
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @param alphasize the maximum character integer representation in the pattern or text to search
     * @return the index of the first
     */
    public static int search(String text, String pattern, int alphasize){
        int[] badChar = BoyerMoore.badCharacter(pattern,alphasize);
        int[] goodSuffix = BoyerMoore.goodSuffix(pattern);
        int i = 0; int j = 0; int m = pattern.length(); int n = text.length();
        while(i<=n-m){
            j = m-1;
            while(j>=0 && pattern.charAt(j) == text.charAt(i+j)){j--;}
            if(j<0) {
                return i;
            }else{
                i+=Math.max(goodSuffix[j+1],j-badChar[(int)text.charAt(i+j)]);
            }
        }
        return -1;
    }

    /**
     * Find the largest integer representation of the characters in a given string
     * @param s string to find largest character
     * @return integer representation of the largest character
     */
    public static int alphasize(String s){
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            if((int) s.charAt(i) > max){
                max = (int) s.charAt(i);
            }
        }
        System.out.println(max);
        return max;
    }
}
