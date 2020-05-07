import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    //File IO
    Tests() throws FileNotFoundException {
    }
    String ElephantsChildTXT = Main.fileHandlerToString("ElephantsChild.txt",StandardCharsets.ISO_8859_1);


    @Test
    void baselineTest() {
        assertEquals(6,BruteForce.bruteStringSearch("Lorem ipsum dolor sit amet","ipsum"));
        assertEquals(6,Horspool.search("ipsum","Lorem ipsum dolor sit amet"));
        //todo liam's method
    }

    @Test
    void elephantsChildTest() throws FileNotFoundException {
        //file IO
        String STRING_ElephantsChild = Main.fileHandlerToString("ElephantsChild.txt", StandardCharsets.ISO_8859_1);
        //tests
        assertEquals(33,BruteForce.bruteStringSearch(STRING_ElephantsChild,"Kipling"));
    }
}