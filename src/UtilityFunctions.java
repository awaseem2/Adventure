import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class UtilityFunctions {

    /** Takes the String of a room and searches for the Room object it is tied to.
     *
     * @param roomName the String desired to find the Room of.
     * @return a Room object of the desired room.
     */
    public static Room convertToRoom(String roomName) {
        for (Room room : Environment.getMap().getRooms()) {
            if (room.getName().equalsIgnoreCase(roomName)) {
                return room;
            }
        }

        return null;
    }

    /** Lists the player's inventory separated by commas.
     *
     * @param items the player's inventory.
     * @return a String of the inventory.
     */
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

    /** Converts the file name to a parsable file.
     *
     * @param filename The name of the file desired to be used.
     * @return a String of the contents of the file.
     */
    public static String getFileContentsAsString(String filename) {
        final Path path = FileSystems.getDefault().getPath("data", filename);
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + filename);
            System.exit(-1);
            return null;
        }
    }
}
