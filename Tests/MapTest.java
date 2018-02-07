import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @Before
    public void setUp() throws Exception {
        DataLoader.initializeMapFromUrl
                ("https://api.myjson.com/bins/10cmpl");
    }

    @Test
    public void getStartingRoom() {
        assertEquals("MatthewsStreet", Environment.getMap().getStartingRoom());
    }

    @Test
    public void getEndingRoom() {
        assertEquals("ACM", Environment.getMap().getEndingRoom());
    }

    @Test
    public void getRooms() {
        assertEquals(6, Environment.getMap().getRooms().size());
    }
}
