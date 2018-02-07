import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    @Before
    public void setUp() throws Exception {
        DataLoader.initializeMapFromUrl
                ("https://api.myjson.com/bins/10cmpl");
    }

    @Test
    public void getName() {
        assertEquals("MatthewsStreet", Environment.getMap().getRooms().get(0).getName());
    }

    @Test
    public void getDescription() {
        assertEquals("You are on Matthews, outside the Siebel Center." +
                        " Angrave is drenched in sweat as he locks up the bike he rode to Siebel.",
                Environment.getMap().getRooms().get(0).getDescription());
    }

    @Test
    public void getItems() {
        assertEquals(1,  Environment.getMap().getRooms().get(1).getItems().size());
    }

    @Test
    public void getDirections() {
        assertEquals(3,  Environment.getMap().getRooms().get(1).getDirections().size());
    }
}
