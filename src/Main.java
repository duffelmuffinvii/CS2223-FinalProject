import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("CS 2223 - Final Project D-Term 2020\nGROUP MEMBERS: Cole Manning, Mason Powell, Liam McDonald, Sam Kwok, Sam Rowe\n------------");
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
