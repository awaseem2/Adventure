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

    /** Takes the String of a room and searches for the Room object it is tied to.
     *
     * @param monsterName the String desired to find the Monster of.
     * @return a Monster object of the desired monster.
     */
    public static Monster convertToMonster(String monsterName) {
        for (Monster monster : Environment.getMap().getMonsters()) {
            if (monster.getName().equalsIgnoreCase(monsterName)) {
                return monster;
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

    /** Lists the monsters separated by commas.
     *
     * @param monsters the monsters in the room.
     * @return a String of the monsters.
     */
    public static String monstersAsString(ArrayList<String> monsters) {
        String allItems = "";

        if (monsters == null) {
            return "";
        }

        if (monsters.isEmpty()) {
            return "nothing";
        }

        for (int i = 0; i < monsters.size(); i++) {
            allItems += monsters.get(i);
            if (i != monsters.size() - 1) {
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

    /** Helper method for userInput to handle the rest of the command.
     *
     * @param inputArray The player's command split by spaces into an Array of String.
     * @return A String of the player's command after the very first word.
     */
    public static String inputAfterAction(String[] inputArray){
        StringBuilder builder = new StringBuilder();

        for(int i = 1; i < inputArray.length; i++){
            builder.append(inputArray[i]);
            if(i != inputArray.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }
}
