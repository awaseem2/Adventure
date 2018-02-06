import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private String name;
    private String description;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<Direction> directions = new ArrayList<>();

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
