import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private ArrayList<String> items;
    private ArrayList<Direction> directions;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public ArrayList<Direction> getDirections() {
        return directions;
    }
}
