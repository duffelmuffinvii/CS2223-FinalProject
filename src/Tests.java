import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    //File IO
    Tests() throws FileNotFoundException {
    } //constructor with exception
    String ElephantsChildTXT = Main.fileHandlerToString("ElephantsChild.txt",StandardCharsets.ISO_8859_1); //convert Elephants file to String
    String MobyDickTXT = Main.fileHandlerToString("moby10b.txt",StandardCharsets.UTF_8);
    String simpleLoremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";


    @Test
    void baselineTest() {
        assertEquals(6,BruteForce.bruteStringSearch(simpleLoremIpsum,"ipsum"));
        assertEquals(6,Horspool.search("ipsum",simpleLoremIpsum));
        ArrayList<Integer> testArray = new ArrayList<>(); testArray.add(6);
        assertEquals(testArray,Horspool.searchAll("ipsum", simpleLoremIpsum));
        //todo liam's method
    }

    @Test
    void elephantsChildRecommendedByEnglingTest() {
        assertEquals(3475,BruteForce.bruteStringSearch(ElephantsChildTXT,"Bi-Coloured-Python-Rock-Snake"));
        assertEquals(3475,Horspool.search("Bi-Coloured-Python-Rock-Snake", ElephantsChildTXT));
        ArrayList<Integer> testArrayList = new ArrayList<Integer>(Arrays.asList(3475, 3694, 3889, 4345, 5488, 6637, 7006, 7828, 8316, 8467, 8747, 8971, 9168, 9745, 10077, 10508, 10768, 10882, 11854));
        assertEquals(testArrayList,Horspool.searchAll("Bi-Coloured-Python-Rock-Snake", ElephantsChildTXT)); //todo CHECK
        //todo liam's method
    }

    //todo capital sensitive test

    @Test
    void veryFarIntoTextWithNonAlphaCharTest() {
        assertEquals(592400,BruteForce.bruteStringSearch(MobyDickTXT,"ice-isles,"));
        assertEquals(592400,Horspool.search("ice-isles,",MobyDickTXT));
        ArrayList<Integer> testArray = new ArrayList<>(); testArray.add(592400);
        assertEquals(testArray,Horspool.searchAll("ice-isles,",MobyDickTXT));
        //todo liam's method
    }

    @Test
    void firstWordTest() {
        assertEquals(0,BruteForce.bruteStringSearch(simpleLoremIpsum,"Lorem"));
        assertEquals(0,Horspool.search("Lorem",simpleLoremIpsum));
        ArrayList<Integer> testArray = new ArrayList<>(); testArray.add(0);
        assertEquals(testArray,Horspool.searchAll("Lorem", simpleLoremIpsum));
        //todo liam's method
    }

    @Test
    void lastWordTest() {
        assertEquals(51,BruteForce.bruteStringSearch(simpleLoremIpsum,"elit"));
        assertEquals(51,Horspool.search("elit",simpleLoremIpsum));
        ArrayList<Integer> testArray = new ArrayList<>(); testArray.add(51);
        assertEquals(testArray,Horspool.searchAll("elit", simpleLoremIpsum));
        //todo liam's method
    }

    @Test
    void partialWordTest() {
        assertEquals(30,BruteForce.bruteStringSearch(simpleLoremIpsum,"nsecte"));
        assertEquals(30,Horspool.search("nsecte",simpleLoremIpsum));
        ArrayList<Integer> testArray = new ArrayList<>(); testArray.add(30);
        assertEquals(testArray,Horspool.searchAll("nsecte", simpleLoremIpsum));
        //todo liam's method
    }

    @Test
    void moreThanOneWordTest() {
        assertEquals(32,BruteForce.bruteStringSearch(simpleLoremIpsum,"ectetur adipisc"));
        assertEquals(32,Horspool.search("ectetur adipisc",simpleLoremIpsum));
        ArrayList<Integer> testArray = new ArrayList<>(); testArray.add(32);
        assertEquals(testArray,Horspool.searchAll("ectetur adipisc", simpleLoremIpsum));
        //todo liam's method
    }

    @Test
    void multipleLineTest() {
        assertEquals(86,BruteForce.bruteStringSearch(ElephantsChildTXT,"O Best Beloved, had\n" +
                "no trunk. He had only a blackish, bulgy nose, as big as a boot,"));
        assertEquals(86,Horspool.search("O Best Beloved, had\n" +
                "no trunk. He had only a blackish, bulgy nose, as big as a boot,",ElephantsChildTXT));
        ArrayList<Integer> testArray = new ArrayList<>(); testArray.add(86);
        assertEquals(testArray,Horspool.searchAll("O Best Beloved, had\n" +
                "no trunk. He had only a blackish, bulgy nose, as big as a boot,", ElephantsChildTXT));
        //todo liam's method
    }

    //todo end of file test (word that is at end of file, but has extra stuff after where file end)

    @Test
    void doesNotOccurInTextButIsSimilarToSomethingThatDoes() { //added em dash at end instead of en dash,
        assertEquals(-1,BruteForce.bruteStringSearch(ElephantsChildTXT,"Bi-Coloured-Python-Rock—Snake"));
        assertEquals(-1,Horspool.search("Bi-Coloured-Python-Rock—Snake",ElephantsChildTXT));
        ArrayList<Integer> testArray = new ArrayList<>();
        assertEquals(testArray,Horspool.searchAll("Bi-Coloured-Python-Rock—Snake", ElephantsChildTXT));
        //todo liam's method
    }

    @Test
    void doesNotOccurInTextButIsSimilarToSomethingThatDoesREDUX() { //threw an umlaut o (ö) in the mix,
        assertEquals(-1,BruteForce.bruteStringSearch(ElephantsChildTXT,"End of this Pröject"));
        assertEquals(-1,Horspool.search("End of this Pröject",ElephantsChildTXT));
        ArrayList<Integer> testArray = new ArrayList<>();
        assertEquals(testArray,Horspool.searchAll("End of this Pröject", ElephantsChildTXT));
        //todo liam's method
    }

    @Test
    void doesNotOccurInTextButIsSimilarToSomethingThatDoesREDUXCounterClockwiseCountourIntegral() { //threw in what i think is the last character in the Unicode Alphabet
        assertEquals(-1,BruteForce.bruteStringSearch(ElephantsChildTXT,"\uDBFF")); // Which is the "Plane 16 Private Use, Last"
        assertEquals(-1,Horspool.search("\uDBFF",ElephantsChildTXT));
        ArrayList<Integer> testArray = new ArrayList<>();
        assertEquals(testArray,Horspool.searchAll("\uDBFF", ElephantsChildTXT));
        //todo liam's method
    }
}