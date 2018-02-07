import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class UtilityFunctions {
    public static Room convertToRoom(String roomName) {
        for (Room room : Environment.getMap().getRooms()) {
            if (room.getName().equalsIgnoreCase(roomName)) {
                return room;
            }
        }

        return null;
    }

    public static Item convertToItem(String itemName) {
        Room currentRoom = Player.getCurrentRoom();
        int currentRoomIndex = Environment.getMap().getRooms().indexOf(currentRoom);
        for (Item item : Environment.getMap().getRooms().get(currentRoomIndex).getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }

        for (Item item : Player.getCurrentItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }

        return null;
    }

    public static String itemsAsString(ArrayList<Item> items) {
        String allItems = "";

        if (items == null) {
            return "";
        }

        if (items.isEmpty()) {
            return "nothing";
        }

        for (int i = 0; i < items.size(); i++) {
            allItems += items.get(i).getName();
            if (i != items.size() - 1) {
                allItems += ", ";
            }
        }

        return allItems;
    }

    public static String getFileContentsAsString(String filename) {
        final Path path = FileSystems.getDefault().getPath("data", filename);
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + filename);
            System.exit(-1);
            return null;  // note that this return will never execute, but Java wants it there.
        }
    }
}
