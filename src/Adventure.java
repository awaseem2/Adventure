import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

    private static Map map = new Map();
    private static Player player = new Player();
    private static String currentRoomString;
    private static int indexOfCurrentRoom;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            map = DataLoader.mapFromUrl(args[0]);
        }
        catch (UnirestException e) {
            System.out.println("Invalid URL.");
        }

        Room firstRoom = map.getRooms().get(0);
        indexOfCurrentRoom = 0;
        player.setCurrentRoom(firstRoom);
        System.out.println(firstRoom.getDescription());
        System.out.println("Your journey begins here.");
        System.out.println("This room contains " + itemsAsString(firstRoom.getItems()));
        System.out.println("From here, you can go: " +
                firstRoom.getDirections().get(0).getName());
        String[] input = scanner.nextLine().split(" ");
        interpretInput(input);

        while(!currentRoomString.equals(map.getEndingRoom())) {
            Room currentRoomObject = map.getRooms().get(indexOfCurrentRoom);
            System.out.println(currentRoomObject.getDescription());
            System.out.println("This room contains " + itemsAsString(currentRoomObject.getItems()));
            currentRoomObject.printDirections();
            input = scanner.nextLine().split(" ");
            interpretInput(input);
        }

    }

    private static String itemsAsString(ArrayList<String> items){
        String allItems = "";

        if(items == null){
            return "nothing";
        }

        for(String i : items) {
            allItems += i + ", ";
        }

        return allItems;
    }

    private static void interpretInput(String[] userInput) {
        String action = userInput[0];
        boolean found = false;
        if(action.equalsIgnoreCase("go")) {
            String desiredDirection = userInput[1];
            ArrayList<Direction> availableDirections =
                    map.getRooms().get(indexOfCurrentRoom).getDirections();
            for(Direction currentDirection : availableDirections) {
                if(currentDirection.getName().equalsIgnoreCase(desiredDirection)) {
                    found = true;
                    currentRoomString = currentDirection.getRoom();
                    findRoom(currentRoomString);
                }
            }
        }
        if(!found){
            System.out.println("I can’t go ");
            for (String currentWord : userInput) {
                System.out.print(currentWord + " ");
            }
        }
    }

    private static void findRoom(String roomName) {
        ArrayList<Room> rooms = map.getRooms();
        for(Room currentRoom : rooms) {
            if(currentRoom.getName().equalsIgnoreCase(roomName)) {
                indexOfCurrentRoom = rooms.indexOf(currentRoom);
            }
        }
    }
}
