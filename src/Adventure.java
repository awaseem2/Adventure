import com.mashape.unirest.http.exceptions.UnirestException;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {
    private static Scanner scanner = new Scanner(System.in);

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

        Environment.getMap().getPlayer().setCurrentRoom(UtilityFunctions.convertToRoom(
                Environment.getMap().getStartingRoom()));
        Environment.getMap().getPlayer().setCurrentHealth(
                Environment.getMap().getPlayer().getMaxHealth());
    }

    /** Prints the description, items, and directions of the current room.
     * Ends the game when the player reaches the ending room.
     */
    private static void beginGame() {
        //Initial console output
        System.out.println(Environment.getMap().getPlayer().getCurrentRoom().getDescription());
        System.out.println("Your journey begins here.");
        System.out.println("This room contains [items]: " + UtilityFunctions.itemsAsString(
                Environment.getMap().getPlayer().getCurrentRoom().getItems()) + " and [monsters]: "
                + UtilityFunctions.monstersAsString(
                        Environment.getMap().getPlayer().getCurrentRoom().getMonstersInRoom()));
        System.out.println(
                Environment.getMap().getPlayer().getCurrentRoom().getNpc().get(0).getName()
                        + " is in this room.");

        if(Environment.getMap().getPlayer().getCurrentRoom().getMonstersInRoom().isEmpty()) {
            System.out.println("From here, you can go: " +
                    Environment.getMap().getPlayer().getCurrentRoom()
                            .getDirections().get(0).getName());
        }

        String[] input = scanner.nextLine().split(" ", 3);
        handleInput(input);

        while (!Environment.getMap().getPlayer().getCurrentRoom().equals(
                UtilityFunctions.convertToRoom(Environment.getMap().getEndingRoom()))) {
            if(!Environment.getMap().getPlayer().isInDuel()) {
                printDescription();
            }
            input = scanner.nextLine().split(" ", 3);
            handleInput(input);
        }

        System.out.println(Environment.getMap().getPlayer().getCurrentRoom().getDescription());
        System.out.println("You have reached your final destination.");
    }

    /** Prints the room the player is in, what it contains, and the available directions.  */
    private static void printDescription() {
        System.out.println(Environment.getMap().getPlayer().getCurrentRoom().getDescription());
        System.out.println("This room contains [items]: " +
                UtilityFunctions.itemsAsString(
                        Environment.getMap().getPlayer().getCurrentRoom().getItems()) +
                " and [monsters]: " +
                UtilityFunctions.monstersAsString(
                        Environment.getMap().getPlayer().getCurrentRoom().getMonstersInRoom()));
        System.out.println(
                Environment.getMap().getPlayer().getCurrentRoom().getNpc().get(0).getName()
                        + " is in this room.");
        if(Environment.getMap().getPlayer().getCurrentRoom().getMonstersInRoom().isEmpty()) {
            Environment.getMap().getPlayer().getCurrentRoom().printDirections();
        }
    }

    private static void handleInput(String[] input) {
        if(!Environment.getMap().getPlayer().isInDuel()) {
            UserInput.interpretInput(input);
        } else {
            DuelInput.interpretInput(input);
        }
    }


}
