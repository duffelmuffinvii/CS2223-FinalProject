import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    @Test
    void baselineTest() {
        assertEquals(6,BruteForce.bruteStringSearch("Lorem ipsum dolor sit amet","ipsum"));
        assertEquals(6,Horspool.search("ipsum","Lorem ipsum dolor sit amet"));
        //todo liam's method
    }
}