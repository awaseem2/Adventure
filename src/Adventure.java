import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Scanner;

public class Adventure {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMap(args[0]);
        beginGame();
        runGame();
    }

    /** Initializes the map and throws an exception if the url isn't valid.
     *  Sets the players current room to the starting room.
     *
     * @param url The url of the json to be used for the game.
     */
    private static void initializeMap(String url) {
        try {
            DataLoader.initializeMapFromUrl(url);
        } catch (UnirestException e) {
            System.out.println("Invalid URL.");
        }

        Map.getLayout().getPlayer().setCurrentRoom(UtilityFunctions.convertToRoom(
                Map.getLayout().getStartingRoom()));
        Map.getLayout().getPlayer().setCurrentHealth(
                Map.getLayout().getPlayer().getMaxHealth());
    }

    /** Prints the description, items, and directions of the first room. */
    private static void beginGame() {
        setGameMode();

        //Initial console output
        System.out.println(Map.getLayout().getPlayer().getCurrentRoom().getDescription());
        System.out.println("Your journey begins here.");
        System.out.println("This room contains [items]: " + UtilityFunctions.itemsAsString(
                Map.getLayout().getPlayer().getCurrentRoom().getItems()) + " and [monsters]: "
                + UtilityFunctions.monstersAsString(
                        Map.getLayout().getPlayer().getCurrentRoom().getMonstersInRoom()));
        System.out.println(
                Map.getLayout().getPlayer().getCurrentRoom().getNpc().get(0).getName()
                        + " is in this room.");

        if(Map.getLayout().getPlayer().getCurrentRoom().getMonstersInRoom().isEmpty()) {
            System.out.println("From here, you can go: " +
                    Map.getLayout().getPlayer().getCurrentRoom()
                            .getDirections().get(0).getName());
        }

        String[] input = scanner.nextLine().split(" ", 3);
        handleInput(input);
    }

    /** Prints description of the current room and handles takes in the user input.
     *  Ends the game when the player reaches the ending room. */
    private static void runGame() {
        while (!Map.getLayout().getPlayer().getCurrentRoom().equals(
                UtilityFunctions.convertToRoom(Map.getLayout().getEndingRoom()))) {
            if(!Map.getLayout().getPlayer().isInDuel()) {
                printDescription();
            }
            String[] input = scanner.nextLine().split(" ", 3);
            handleInput(input);
        }

        System.out.println(Map.getLayout().getPlayer().getCurrentRoom().getDescription());
        System.out.println("You have reached your final destination.");
    }

    /** Prints the room the player is in, what it contains, and the available directions.  */
    private static void printDescription() {
        System.out.println(Map.getLayout().getPlayer().getCurrentRoom().getDescription());
        System.out.println("This room contains [items]: " +
                UtilityFunctions.itemsAsString(
                        Map.getLayout().getPlayer().getCurrentRoom().getItems()) +
                " and [monsters]: " +
                UtilityFunctions.monstersAsString(
                        Map.getLayout().getPlayer().getCurrentRoom().getMonstersInRoom()));
        System.out.println(
                Map.getLayout().getPlayer().getCurrentRoom().getNpc().get(0).getName()
                        + " is in this room.");
        if(Map.getLayout().getPlayer().getCurrentRoom().getMonstersInRoom().isEmpty()) {
            Map.getLayout().getPlayer().getCurrentRoom().printDirections();
        }
    }

    /** Decides to use User Input vs Duel Input depending on whether the player is in a duel */
    private static void handleInput(String[] input) {
        if(Map.getLayout().getPlayer().isInDuel()) {
            DuelInput.interpretInput(input);
        } else {
            UserInput.interpretInput(input);
        }
    }

    /** Asks user what mode they want to play in until they enter a valid one. */
    private static void setGameMode() {
        System.out.println("Would you like to play in hard, normal, or easy?");
        String input = scanner.nextLine();

        while(!handleGameMode(input)) {
            System.out.println("Would you like to play in hard, normal, or easy?");
            input = scanner.nextLine();
            handleGameMode(input);
        }
    }

    /** Takes the user's input and assigns the attack, defense, and max health accordingly.
     *
     * @param gameMode The game mode the user inputs to play in.
     * @return true if the user input a valid response.
     */
    private static boolean handleGameMode(String gameMode) {
        boolean setGameMode = false;
        switch(gameMode.toLowerCase()) {
            case "hard":
                Map.getLayout().getPlayer().setAttack(7.0);
                Map.getLayout().getPlayer().setDefense(4.0);
                Map.getLayout().getPlayer().setMaxHealth(11.0);
                Map.getLayout().getPlayer().setCurrentHealth(11.0);
                setGameMode = true;
                break;

            case "normal":
                Map.getLayout().getPlayer().setAttack(10.0);
                Map.getLayout().getPlayer().setDefense(5.0);
                Map.getLayout().getPlayer().setMaxHealth(25.0);
                Map.getLayout().getPlayer().setCurrentHealth(25.0);
                setGameMode = true;
                break;

            case "easy":
                Map.getLayout().getPlayer().setAttack(1000);
                Map.getLayout().getPlayer().setDefense(1000);
                Map.getLayout().getPlayer().setMaxHealth(1000);
                Map.getLayout().getPlayer().setCurrentHealth(1000);
                setGameMode = true;
                break;

            default:
                System.out.println("Invalid level.");
                break;
        }

        return setGameMode;
    }


}
