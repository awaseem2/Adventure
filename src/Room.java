import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Npc> npc = new ArrayList<>();
    private ArrayList<Direction> directions = new ArrayList<>();
    private ArrayList<String> monstersInRoom = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Npc> getNpc() {
        return npc;
    }

    public ArrayList<Direction> getDirections() {
        return directions;
    }

    public ArrayList<String> getMonstersInRoom() {
        return monstersInRoom;
    }

    /** Prints a list separated by commas of the valid directions the player can move in. */
    public void printDirections() {
        System.out.print("From here, you can go: ");
        for(int i = 0; i < directions.size(); i++) {
            System.out.print(directions.get(i).getName());
            if(i != directions.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) &&
                Objects.equals(description, room.description) &&
                Objects.equals(items, room.items) &&
                Objects.equals(directions, room.directions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, items, directions);
    }
}
