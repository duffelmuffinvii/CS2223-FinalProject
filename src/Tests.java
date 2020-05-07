import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    //File IO
    Tests() throws FileNotFoundException {
    } //constructor with exception
    String ElephantsChildTXT = Main.fileHandlerToString("ElephantsChild.txt",StandardCharsets.ISO_8859_1); //convert Elephants file to String
    String MobyDickTXT = Main.fileHandlerToString("moby10b.txt",StandardCharsets.UTF_8);


    @Test
    void baselineTest() {
        assertEquals(6,BruteForce.bruteStringSearch("Lorem ipsum dolor sit amet","ipsum"));
        assertEquals(6,Horspool.search("ipsum","Lorem ipsum dolor sit amet"));
        //todo Horspool.searchAll
        //todo liam's method
    }

    @Test
    void elephantsChildTest() {
        assertEquals(33,BruteForce.bruteStringSearch(ElephantsChildTXT,"Kipling"));
        assertEquals(33,Horspool.search("Kipling", ElephantsChildTXT));
        //todo Horspool.searchAll
        //todo liam's method
    }

    //todo capital sensitive test

    @Test
    void veryFarIntoTextWithNonAlphaCharTest() {
        assertEquals(592400,BruteForce.bruteStringSearch(MobyDickTXT,"ice-isles,"));
        assertEquals(592400,Horspool.search("ice-isles,",MobyDickTXT));
        //todo Horspool.searchAll
        //todo liam's method
    }

    //todo first word test

    //todo last word test

    //todo partial word test

    //todo more than one word test

    //todo multiple line test

    //todo end of file test (word that is at end of file, but has extra stuff after where file end)
}