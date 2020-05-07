import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("CS 2223 - Final Project D-Term 2020\nGROUP MEMBERS: Cole Manning, Mason Powell, Liam McDonald, Sam Kwok, Sam Rowe\n------------");

        System.out.println("Example Implementation: (NOTE: some of these take a long time to search expecially if the word is not in the text, or is towards the end)\n");
        System.out.println("What text would you like to search through?\n1. The Elephant's Child, 2. Moby Dick. Enter corresponding number...");
        String file;
        Scanner keyboard = new Scanner(System.in);
        int textOption = keyboard.nextInt();
        switch (textOption) {
            case 1: file = fileHandlerToString("ElephantsChild.txt",StandardCharsets.ISO_8859_1);
                break;
            case 2: file = fileHandlerToString("moby10b.txt",StandardCharsets.UTF_8);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + textOption + ". Please a valid option!");
        }
        System.out.println("\nWhat string would you like to search for? Enter it now...");
        String toSearchOption = keyboard.next();
        System.out.println("\nWhat method would you like to run?\nOptions are 1. Brute Force' 2. Horspool's Algorithm, 3. Horspool's Algorithm Implementation (find multiple matches Version), or 4. Boyer Moore Algorithm Implementation? Enter corresponding number...");
        int result = -2;
        switch (keyboard.nextInt()) {
            case 1:
                result = BruteForce.bruteStringSearch(file,toSearchOption);
                if (result == -1) {
                    System.out.println("\n"+toSearchOption+" does not appear in the text.");
                } else System.out.println("\n"+toSearchOption + " is first seen in the text at index "+result);
                break;
            case 2:
                result = Horspool.search(toSearchOption,file);
                if (result == -1) {
                    System.out.println("\n"+toSearchOption+" does not appear in the text.");
                } else System.out.println("\n"+toSearchOption + " is first seen in the text at index "+result);
                break;
            case 3:
                ArrayList<Integer> resultSearchAll = Horspool.searchAll(toSearchOption,file);
                if (resultSearchAll.isEmpty()) {
                    System.out.println("\n"+toSearchOption+" does not appear in the text.");
                } else System.out.println("\n"+toSearchOption+" is seen in the text at the following indexes: "+resultSearchAll);
                break;
            case 4:
                result = BoyerMoore.search(file,toSearchOption,Math.max(BoyerMoore.alphasize(toSearchOption),BoyerMoore.alphasize(file)));
                if (result == -1) {
                    System.out.println("\n"+toSearchOption+" does not appear in the text.");
                } else System.out.println("\n"+toSearchOption + " is first seen in the text at index "+result);
                break;
        }
    }

    /**
     * Converts txt (and maybe others depending on charset) to string.
     * @param filename full file name (File should be in project/root directory).
     * @param charset encoding charset.
     * @return String representation of file.
     * @throws FileNotFoundException if no file of that name is found.
     */
    public static String fileHandlerToString(String filename, Charset charset) throws FileNotFoundException {
        String content = "";
        File FILE = new File(filename);
        if (!FILE.exists()) throw new FileNotFoundException("File \""+FILE+"\" was not found");
        Scanner scanner = new Scanner(FILE, String.valueOf(charset));
        while (scanner.hasNextLine()) {
            content = content.concat(scanner.nextLine().concat("\n"));
        }
        return content;
    }
}
