import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Player {
    private String name;
    @SerializedName("items") private ArrayList<Item> currentItems = new ArrayList<>();
    private Room currentRoom;
    private double attack;
    private double defense;
    @SerializedName("health")private double maxHealth;
    private int level;
    private boolean isInDuel;
    private double currentHealth = maxHealth;
    private double experience;
    private String currentOpponent;

    public ArrayList<Item> getCurrentItems() {
        return currentItems;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isInDuel() {
        return isInDuel;
    }

    public void setIsInDuel(boolean isInDuel) {
        this.isInDuel = isInDuel;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public String getCurrentOpponent() {
        return currentOpponent;
    }

    public void setCurrentOpponent(String currentOpponent) {
        this.currentOpponent = currentOpponent;
    }

}
