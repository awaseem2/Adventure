import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @Before
    public void setUp() throws UnirestException{
        DataLoader.initializeMapFromUrl
                ("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
    }

    @Test
    public void getStartingRoom() {
        assertEquals("MatthewsStreet", Environment.getMap().getStartingRoom());
    }

    @Test
    public void getEndingRoom() {
        assertEquals("Siebel1314", Environment.getMap().getEndingRoom());
    }

    @Test
    public void getRooms() {
        assertEquals(8, Environment.getMap().getRooms().size());
    }
}