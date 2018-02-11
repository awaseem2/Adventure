import com.mashape.unirest.http.exceptions.UnirestException;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {
    private static Scanner scanner = new Scanner(System.in);
    private static Player player = new Player();

    public static void main(String[] args) {
        initializeMap(args[0]);
        beginGame();
    }

    /** Initializes the map and throws an exception if the url isn't valid.
     * Sets the players current room to the starting room.
     *
     * @param url The url of the json to be used for the game.
     */
    private static void initializeMap(String url) {
        try {
            DataLoader.initializeMapFromUrl(url);

        } catch (UnirestException e) {
            System.out.println("Invalid URL.");
        }

        player.setCurrentRoom(UtilityFunctions.convertToRoom(
                Environment.getMap().getStartingRoom()));
    }

    /** Prints the description, items, and directions of the current room.
     * Ends the game when the player reaches the ending room.
     */
    private static void beginGame() {
        //Initial console output
        System.out.println(Player.getCurrentRoom().getDescription());
        System.out.println("Your journey begins here.");
        System.out.println("This room contains " + UtilityFunctions.itemsAsString(
                Player.getCurrentRoom().getItems()));
        System.out.println(Player.getCurrentRoom().getNpc().get(0).getName() + " is in this room.");
        if(Player.getCurrentRoom().getMonstersInRoom().isEmpty()) {
            System.out.println("From here, you can go: " +
                    Player.getCurrentRoom().getDirections().get(0).getName());
        }
        String[] input = scanner.nextLine().split(" ");
        UserInput.interpretInput(input);

        while (!Player.getCurrentRoom().equals(
                UtilityFunctions.convertToRoom(Environment.getMap().getEndingRoom()))) {
            printDescription();
            input = scanner.nextLine().split(" ");
            UserInput.interpretInput(input);
        }

        System.out.println(Player.getCurrentRoom().getDescription());
        System.out.println("You have reached your final destination.");
    }

    /** Prints the room the player is in, what it contains, and the available directions.  */
    private static void printDescription() {
        System.out.println(Player.getCurrentRoom().getDescription());
        System.out.println("This room contains [items]: " +
                UtilityFunctions.itemsAsString(Player.getCurrentRoom().getItems()) +
                " and [monsters]: " +
                UtilityFunctions.monstersAsString(Player.getCurrentRoom().getMonstersInRoom()));
        System.out.println(Player.getCurrentRoom().getNpc().get(0).getName() + " is in this room.");
        if(Player.getCurrentRoom().getMonstersInRoom().isEmpty()) {
            Player.getCurrentRoom().printDirections();
        }
    }

}
