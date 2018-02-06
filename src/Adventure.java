import com.mashape.unirest.http.exceptions.UnirestException;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

    private static Scanner scanner = new Scanner(System.in);
    private static Player player = new Player();

    public static void main(String[] args) {
        try {
            DataLoader.initializeMapFromUrl(args[0]);
        } catch (UnirestException e) {
            System.out.println("Invalid URL.");
        }

        //MapValidator.testMapIsValid();
        player.setCurrentRoom(UtilityFunctions.convertToRoom(Environment.getMap().getStartingRoom()));

        System.out.println(Player.getCurrentRoom().getDescription());
        System.out.println("Your journey begins here.");
        System.out.println("This room contains " + UtilityFunctions.itemsAsString(Player.getCurrentRoom().getItems()));
        System.out.println("From here, you can go: " +
                Player.getCurrentRoom().getDirections().get(0).getName());
        String[] input = scanner.nextLine().split(" ");
        UserInput.interpretInput(input);

        while (!Player.getCurrentRoom().equals(
                UtilityFunctions.convertToRoom(Environment.getMap().getEndingRoom()))) {
            printDescription();
            input = scanner.nextLine().split(" ");
            UserInput.interpretInput(input);
        }

        System.out.println(Player.getCurrentRoom().getDescription());
        System.out.println("Congratulations, you reached the ending room!");

    }

    private static void printDescription() {
        System.out.println(Player.getCurrentRoom().getDescription());
        System.out.println("This room contains " +
                UtilityFunctions.itemsAsString(Player.getCurrentRoom().getItems()));
        Player.getCurrentRoom().printDirections();
    }

}