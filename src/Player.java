import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Player {
    private static String name;
    @SerializedName("items") private static ArrayList<Item> currentItems = new ArrayList<>();
    private static Room currentRoom;
    private static double attack;
    private static double defense;
    @SerializedName("health")private static double maxHealth;
    private static int level;
    private static boolean isInDuel;
    private static double currentHealth = maxHealth;
    private static double experience;

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

    public static void setAttack(double attack) {
        Player.attack = attack;
    }

    public static double getDefense() {
        return defense;
    }

    public static void setDefense(double defense) {
        Player.defense = defense;
    }

    public static double getMaxHealth() {
        return maxHealth;
    }

    public static void setMaxHealth(double maxHealth) {
        Player.maxHealth = maxHealth;
    }

    public static double getCurrentHealth() {
        return currentHealth;
    }

    public static void setCurrentHealth(double currentHealth) {
        Player.currentHealth = currentHealth;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Player.level = level;
    }

    public static boolean isInDuel() {
        return isInDuel;
    }

    public static void setIsInDuel(boolean isInDuel) {
        Player.isInDuel = isInDuel;
    }

    public static double getExperience() {
        return experience;
    }

    public static void setExperience(double experience) {
        Player.experience = experience;
    }
}
