import java.util.ArrayList;
import java.util.HashMap;

public class Layout {
    private String startingRoom;
    private String endingRoom;
    private ArrayList<Room> rooms;
    private Player player;
    private ArrayList<Monster> monsters;
    private HashMap<String, Monster> monsterMap = new HashMap<>();

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

    public HashMap<String, Monster> getMonsterMap() {
        return monsterMap;
    }

    /** Creates hashmap of Monster objects and their names. */
    public void initializeMonsterMap() {
        for (Monster monster : monsters) {
            monster.setCurrentHealth(monster.getStartingHealth());
            monsterMap.put(monster.getName().toLowerCase(), monster);
        }
    }
}
