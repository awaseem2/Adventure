import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    @Before
    public void setUp() throws Exception {
        DataLoader.initializeMapFromUrl
                ("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
    }

    @Test
    public void getName() {
        assertEquals("East",
                Environment.getMap().getRooms().get(0).getDirections().get(0).getName());
    }

    @Test
    public void getRoom() {
        assertEquals("SiebelEntry",
                Environment.getMap().getRooms().get(0).getDirections().get(0).getRoom());
    }
}