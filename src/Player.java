import java.util.ArrayList;

public class Player {
    private ArrayList<String> currentItems = new ArrayList<>();
    private Room currentRoom;

    public ArrayList<String> getCurrentItems() {
        return currentItems;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentItems(ArrayList<String> currentItems) {
        this.currentItems = currentItems;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
