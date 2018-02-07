import java.util.ArrayList;

public class Player {
    private static ArrayList<Item> currentItems = new ArrayList<>();
    private static Room currentRoom;

    public static ArrayList<Item> getCurrentItems() {
        return currentItems;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentItems(ArrayList<Item> currentItems) {
        Player.currentItems = currentItems;
    }

    public static void setCurrentRoom(Room currentRoom) {
        Player.currentRoom = currentRoom;
    }
}
