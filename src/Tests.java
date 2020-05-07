import org.junit.jupiter.api.Test;
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
    int ElephantsChild_CharSize = BoyerMoore.alphasize(ElephantsChildTXT);
    int MobyDick_CharSize = BoyerMoore.alphasize(MobyDickTXT);
    int simpleLoremIpsum_CharSize = BoyerMoore.alphasize(simpleLoremIpsum);

    /**
     * If ONE result is expected from search, runs test on all functions
     * @param searchFor the string ot search for
     * @param fullText the full text to search through
     * @param expected first expected result
     * @param alphaSize largest integer representation of alphabet of input text and text to search.
     */
    private static void runTestsOneResultExpected(String searchFor, String fullText, int expected, int alphaSize) {
        assertEquals(expected,BruteForce.bruteStringSearch(fullText,searchFor));
        assertEquals(expected,Horspool.search(searchFor,fullText));
        assertEquals(expected,BoyerMoore.search(fullText,searchFor,alphaSize));
        ArrayList<Integer> testArray = new ArrayList<>();
        testArray.add(expected);
        assertEquals(testArray,Horspool.searchAll(searchFor, fullText));
    }

    /**
     * If MULTIPLE results expected from search, runs tests on all functions
     * @param searchFor the string ot search for
     * @param fullText the full text to search through
     * @param expected first expected result
     * @param arrayList Array of expected results
     * @param alphaSize largest integer representation of alphabet of input text and text to search.
     */
    private static void runTestsMultipleResultsExpected(String searchFor, String fullText, int expected, ArrayList<Integer> arrayList, int alphaSize) {
        assertEquals(expected,BruteForce.bruteStringSearch(fullText,searchFor));
        assertEquals(expected,Horspool.search(searchFor,fullText));
        assertEquals(expected,BoyerMoore.search(fullText,searchFor,alphaSize));
        assertEquals(arrayList,Horspool.searchAll(searchFor, fullText));
    }

    /**
     * If NO result is expected from search, runs test on all functions
     * @param searchFor the string ot search for
     * @param fullText the full text to search through
     * @param alphaSize largest integer representation of alphabet of input text and text to search.
     */
    private static void runTestsNoResultExpected(String searchFor, String fullText, int alphaSize) {
        assertEquals(-1,BruteForce.bruteStringSearch(fullText,searchFor));
        assertEquals(-1,Horspool.search(searchFor,fullText));
        assertEquals(-1,BoyerMoore.search(fullText,searchFor,alphaSize));
        ArrayList<Integer> testArray = new ArrayList<>();
        assertEquals(testArray,Horspool.searchAll(searchFor, fullText));
    }


    @Test
    void baselineTest() { //Just a initial baseline test to get us up and running
        //setup
        String searchFor = "ipsum";
        String fullText = simpleLoremIpsum;
        int expected = 6;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsOneResultExpected(searchFor, fullText, expected, alphaSize);
    }

    @Test
    void elephantsChildRecommendedByEnglingTest() { //Testing what Engling suggested
        //setup
        String searchFor = "Bi-Coloured-Python-Rock-Snake";
        String fullText = ElephantsChildTXT;
        int expected = 3475;
        int alphaSize = Math.max(ElephantsChild_CharSize,BoyerMoore.alphasize(searchFor));
        ArrayList<Integer> testArrayList = new ArrayList<>(Arrays.asList(3475, 3694, 3889, 4345, 5488, 6637, 7006, 7828, 8316, 8467, 8747, 8971, 9168, 9745, 10077, 10508, 10768, 10882, 11854));

        //run tests
        runTestsMultipleResultsExpected(searchFor, fullText, expected, testArrayList, alphaSize);
    }

    @Test
    void capitalSensitiveTest() { //Searching for capitalized word, which has a lowercase occurrence in the text.
        //setup
        String searchFor = "Ipsum";
        String fullText = simpleLoremIpsum;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsNoResultExpected(searchFor,fullText,alphaSize);
    }

    @Test
    void veryFarIntoTextWithNonAlphaCharTest() { //make sure they make it /very/ far into a long text.
        //setup
        String searchFor = "ice-isles,";
        String fullText = MobyDickTXT;
        int expected = 592400;
        int alphaSize = Math.max(MobyDick_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsOneResultExpected(searchFor, fullText, expected, alphaSize);
    }

    @Test
    void firstWordTest() { //testing the first word of the file, index 0.
        //setup
        String searchFor = "Lorem";
        String fullText = simpleLoremIpsum;
        int expected = 0;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsOneResultExpected(searchFor, fullText, expected, alphaSize);
    }

    @Test
    void lastWordTest() { //testing last word of file
        //setup
        String searchFor = "elit.";
        String fullText = simpleLoremIpsum;
        int expected = 51;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsOneResultExpected(searchFor, fullText, expected, alphaSize);
    }

    @Test
    void partialWordTest() { //Testing search in the middle of a word.
        //setup
        String searchFor = "nsecte";
        String fullText = simpleLoremIpsum;
        int expected = 30;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsOneResultExpected(searchFor, fullText, expected, alphaSize);
    }

    @Test
    void moreThanOneWordSearchTest() { //testing with multiple words being searched for.
        //setup
        String searchFor = "ectetur adipisc";
        String fullText = simpleLoremIpsum;
        int expected = 32;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsOneResultExpected(searchFor, fullText, expected, alphaSize);
    }

    @Test
    void multipleLineTest() { //Searching multiple lines of text to make sure they process line breaks properly.
        //setup
        String searchFor = "O Best Beloved, had\n" + "no trunk. He had only a blackish, bulgy nose, as big as a boot,";
        String fullText = ElephantsChildTXT;
        int expected = 86;
        int alphaSize = Math.max(ElephantsChild_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsOneResultExpected(searchFor, fullText, expected, alphaSize);
    }

    @Test
    void endOfFileTest() {
        //setup
        String searchFor = "elit. "; // "elit." is the last word in the text, but I've added an additional space after it.
        String fullText = simpleLoremIpsum;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));

        runTestsNoResultExpected(searchFor,fullText,alphaSize);
    }

    @Test
    void doesNotOccurInTextButIsSimilarToSomethingThatDoes() { //added em dash at end instead of en dash,
        //setup
        String searchFor = "Bi-Coloured-Python-Rock—Snake";
        String fullText = ElephantsChildTXT;
        int alphaSize = Math.max(ElephantsChild_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsNoResultExpected(searchFor, fullText, alphaSize);
    }

    @Test
    void doesNotOccurInTextButIsSimilarToSomethingThatDoesREDUX() { //threw in what I think is the last character in the Unicode Alphabet
        //setup                                                     // Which is the "Plane 16 Private Use, Last"
        String searchFor = "\uDBFF";
        String fullText = ElephantsChildTXT;
        int alphaSize = Math.max(ElephantsChild_CharSize,BoyerMoore.alphasize(searchFor));

        //run tests
        runTestsNoResultExpected(searchFor, fullText, alphaSize);
    }

    @Test
    void findsAllOccurrencesTest() { //test just for Horspool.SearchAll()
        //setup
        String searchFor = "e";
        String fullText = simpleLoremIpsum;
        int expected = 3;
        int alphaSize = Math.max(simpleLoremIpsum_CharSize,BoyerMoore.alphasize(searchFor));
        ArrayList<Integer> testArrayList = new ArrayList<>(Arrays.asList(3,24,32,35,51));

        //run tests
        runTestsMultipleResultsExpected(searchFor, fullText, expected, testArrayList, alphaSize);
    }
}