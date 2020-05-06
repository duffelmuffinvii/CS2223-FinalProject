public class Main {
    public static void main(String[] args) {
        System.out.println("CS 2223 - Final Project D-Term 2020\nGROUP MEMBERS: Cole Manning, Mason Powell, Liam McDonald, Sam Kwok, Sam Rowe\n------------");
    }

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
