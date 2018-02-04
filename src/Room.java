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

    public void printDirections() {
        System.out.print("From here, you can go: ");
        for (Direction direction : directions) {
            System.out.print(direction.getName() + ", ");
        }
        System.out.println();
    }
}
