import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

    private static Scanner scanner = new Scanner(System.in);
    private static Map map = new Map();
    private static Player player = new Player();
    private static Room currentRoom;
    private static String currentRoomString;
    private static int indexOfCurrentRoom;

    public static void main(String[] args) {

        try {
            map = DataLoader.mapFromUrl(args[0]);
        }
        catch (UnirestException e) {
            System.out.println("Invalid URL.");
        }

        currentRoom = map.getRooms().get(0);
        indexOfCurrentRoom = 0;
        currentRoomString = map.getRooms().get(0).toString();
        player.setCurrentRoom(currentRoom);
        System.out.println(currentRoom.getDescription());
        System.out.println("Your journey begins here.");
        System.out.println("This room contains " + itemsAsString(currentRoom.getItems()));
        System.out.println("From here, you can go: " +
                currentRoom.getDirections().get(0).getName());
        String[] input = scanner.nextLine().split(" ");
        interpretInput(input);

        while(!currentRoomString.equals(map.getEndingRoom())) {
            currentRoom = map.getRooms().get(indexOfCurrentRoom);
            System.out.println(currentRoom.getDescription());
            System.out.println("This room contains " + itemsAsString(currentRoom.getItems()));
            currentRoom.printDirections();
            input = scanner.nextLine().split(" ");
            interpretInput(input);
        }

    }

    private static void interpretInput(String[] userInput) {
        String action = userInput[0];
        String parameter = inputAfterAction(userInput);
        switch(action.toLowerCase()) {
            case "go":
                boolean found = false;
                String desiredDirection = parameter;
                ArrayList<Direction> availableDirections =
                        map.getRooms().get(indexOfCurrentRoom).getDirections();
                for(Direction currentDirection : availableDirections) {
                    if(currentDirection.getName().equalsIgnoreCase(desiredDirection)) {
                        found = true;
                        currentRoomString = currentDirection.getRoom();
                        findRoom(currentRoomString);
                    }
                }
                if(!found){
                    System.out.println("I can’t go " + parameter);
                }
                break;
            case "take":
                if(currentRoom.getItems().contains(parameter)) {
                    player.getCurrentItems().add(parameter);
                    map.getRooms().get(indexOfCurrentRoom).getItems().remove(parameter);
                } else {
                    System.out.println("I can't take " + parameter);
                }
                break;
            case "drop":
                if(player.getCurrentItems().contains(parameter)) {
                    player.getCurrentItems().remove(parameter);
                    map.getRooms().get(indexOfCurrentRoom).getItems().add(parameter);
                } else {
                    System.out.println("I can't drop " + parameter);
                }
                break;
            case "list":
                System.out.println("You are carrying " + itemsAsString(player.getCurrentItems()));
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("I don't understand '" + action  + " " + parameter + "'");
                break;

        }
    }

    private static String itemsAsString(ArrayList<String> items){
        String allItems = "";

        if(items == null) {
            return "";
        }

        if(items.isEmpty()) {
            return "nothing";
        }

        for(String i : items) {
            allItems += i + ", ";
        }

        return allItems;
    }

    private static void findRoom(String roomName) {
        ArrayList<Room> rooms = map.getRooms();
        for(Room currentRoom : rooms) {
            if(currentRoom.getName().equalsIgnoreCase(roomName)) {
                indexOfCurrentRoom = rooms.indexOf(currentRoom);
            }
        }
    }

    private static String inputAfterAction(String[] inputArray){
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
