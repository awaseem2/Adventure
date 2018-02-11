import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Player {
    private static String name;
    @SerializedName("items") private static ArrayList<Item> currentItems = new ArrayList<>();
    private static Room currentRoom;
    private static double attack;
    private static double defense;
    @SerializedName("health")private static double startingHealth;
    private static int level;
    private static boolean isInDuel;
    private static double currentHealth = startingHealth;

    public static ArrayList<Item> getCurrentItems() {
        return currentItems;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Room currentRoom) {
        Player.currentRoom = currentRoom;
    }

    public static String getName() {
        return name;
    }

    public static double getAttack() {
        return attack;
    }

    public static double getDefense() {
        return defense;
    }

    public static double getStartingHealth() {
        return startingHealth;
    }

    public static double getCurrentHealth() {
        return currentHealth;
    }

    public static int getLevel() {
        return level;
    }

    public static boolean isInDuel() {
        return isInDuel;
    }

    public static void setIsInDuel(boolean isInDuel) {
        Player.isInDuel = isInDuel;
    }
}
