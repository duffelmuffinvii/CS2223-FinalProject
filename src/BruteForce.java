public class BruteForce {

    /**
     * Uses brute force to search for text
     * @param sentence Input sentence
     * @param s String to search for
     * @return Index of found text
     */
    public static int bruteStringSearch(String sentence, String s){
        int index = -1;

        for(int i = 0; i < (sentence.length() - s.length() + 1); i++){
            if(sentence.substring(i,i+s.length()).equals(s)){
                return i;
            }
        }

        return index;
    }
}
