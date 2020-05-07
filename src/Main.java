import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("CS 2223 - Final Project D-Term 2020\nGROUP MEMBERS: Cole Manning, Mason Powell, Liam McDonald, Sam Kwok, Sam Rowe\n------------");
        String STRING_ElephantsChild = fileHandlerToString("ElephantsChild.txt",StandardCharsets.ISO_8859_1);
        System.out.println(STRING_ElephantsChild);
    }

    public static String fileHandlerToString(String filename, Charset charset) throws FileNotFoundException {
        String content = "";
        File FILE = new File("ElephantsChild.txt");
        if (!FILE.exists()) throw new FileNotFoundException("File \""+FILE+"\" was not found");
        Scanner scanner = new Scanner(FILE, String.valueOf(charset));
        while (scanner.hasNextLine()) {
            content = content.concat(scanner.nextLine().concat("\n"));
        }
        return content;
    }
}
