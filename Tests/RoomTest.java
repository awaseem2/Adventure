import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    @Before
    public void setUp() throws Exception {
        DataLoader.initializeMapFromUrl
                ("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
    }

    @Test
    public void getName() {
        assertEquals("MatthewsStreet", Environment.getMap().getRooms().get(0).getName());
    }

    @Test
    public void getDescription() {
        assertEquals("You are on Matthews, outside the Siebel Center",
                Environment.getMap().getRooms().get(0).getDescription());
    }

    @Test
    public void getItems() {
        assertEquals(1,  Environment.getMap().getRooms().get(0).getItems().size());
        assertEquals(2,  Environment.getMap().getRooms().get(1).getItems().size());
    }

    @Test
    public void getDirections() {
        assertEquals(1,  Environment.getMap().getRooms().get(0).getDirections().size());
        assertEquals(4,  Environment.getMap().getRooms().get(1).getDirections().size());
    }
}