import java.util.ArrayList;

public class Map {
    private String startingRoom;
    private String endingRoom;
    private ArrayList<Room> rooms;
    private Player player;
    private ArrayList<Monster> monsters;

    public String getStartingRoom() {
        return startingRoom;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
