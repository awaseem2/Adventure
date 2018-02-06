import java.util.ArrayList;

public class UtilityFunctions {
    public static Room convertToRoom(String roomName) {
        for (Room room : Environment.getMap().getRooms()) {
            if (room.getName().equalsIgnoreCase(roomName)) {
                return room;
            }
        }

        return null;
    }

    public static String itemsAsString(ArrayList<String> items) {
        String allItems = "";

        if (items == null) {
            return "";
        }

        if (items.isEmpty()) {
            return "nothing";
        }

        for (int i = 0; i < items.size(); i++) {
            allItems += items.get(i);
            if (i != items.size() - 1) {
                allItems += ", ";
            }
        }

        return allItems;
    }
}
