import java.util.ArrayList;

public class UserInput {

    /** Takes what the user writes in the console and handles the input accordingly
     *
     * @param userInput The player's command split by spaces into an Array of String.
     */
    public static void interpretInput(String[] userInput) {
        String action = userInput[0];
        String desiredNoun = UtilityFunctions.inputAfterAction(userInput);

        switch(action.toLowerCase()) {
            case "go":
                goCommand(desiredNoun);
                break;

            case "take":
                takeCommand(desiredNoun);
                break;

            case "drop":
                dropCommand(desiredNoun);
                break;

            case "list":
                System.out.println("You are carrying " +
                        UtilityFunctions.itemsAsString(Player.getCurrentItems()));
                break;

            case "use":
                useCommand(desiredNoun);
                break;

            case "talk":
                talkCommand(desiredNoun);
                break;

            case "playerinfo":
                System.out.println("Here are your stats:");
                System.out.println("[Level]: " + Player.getLevel());
                System.out.println("[Attack]:" + Player.getAttack());
                System.out.println("[Defense]: " + Player.getDefense());
                System.out.println("[Health]: " + Player.getCurrentHealth());
                break;

            case "duel":
                duelCommand(userInput, desiredNoun);

            case "exit":
                System.exit(0);
                break;

            case "quit":
                System.exit(0);
                break;

            default:
                if(userInput.length == 1) {
                    System.out.println("I don't understand '" + action + "'");
                } else {
                    System.out.println("I don't understand '" + action  + " " + desiredNoun + "'");
                }

                break;

        }
    }

    /** Updates the player's current room if the room is a valid option.
     *  Prints that the player can't go there if it's not possible.
     *
     * @param desiredNoun the place the player inputs to go to.
     */
    private static void goCommand(String desiredNoun) {
        if(!Player.isInDuel()) {
            boolean found = false;
            String desiredDirection = desiredNoun;
            ArrayList<Direction> availableDirections =
                    Player.getCurrentRoom().getDirections();

            for (Direction currentDirection : availableDirections) {
                if (currentDirection.getName().equalsIgnoreCase(desiredDirection)) {
                    found = true;
                    Room newRoom = UtilityFunctions.convertToRoom(currentDirection.getRoom());
                    Player.setCurrentRoom(newRoom);
                    break;
                }
            }

            if (!found) {
                System.out.println("I can’t go " + desiredNoun);
            }

        } else {
            System.out.println("There are still monsters here, I can’t move.");
        }

    }

    /** Updates the player's inventory and removes it from the room if the item is a valid option.
     *  Prints that the player can't take it if it's not possible.
     *
     * @param desiredNoun the item the player inputs to take.
     */
    private static void takeCommand(String desiredNoun) {
        if(!Player.isInDuel()) {
            boolean found = false;

            for (Item item : Player.getCurrentRoom().getItems()) {
                if (item.getName().equalsIgnoreCase(desiredNoun)) {
                    Player.getCurrentItems().add(item);
                    Player.getCurrentRoom().getItems().remove(item);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("I can't take " + desiredNoun);
            }

        } else {
            System.out.println("There are still monsters here, I can’t take that.");
        }

    }

    /** Updates the player's inventory and adds it to the room if the item is a valid option.
     *  Prints that the player can't drop it if it's not possible.
     *
     * @param desiredNoun the item the player inputs to drop.
     */
    private static void dropCommand(String desiredNoun) {
        if(!Player.isInDuel()) {
            boolean found = false;

            for (Item item : Player.getCurrentItems()) {
                if (item.getName().equalsIgnoreCase(desiredNoun)) {
                    Player.getCurrentItems().remove(item);
                    Player.getCurrentRoom().getItems().add(item);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("I can't drop " + desiredNoun);
            }

        } else {
            System.out.println("There are still monsters here, I can’t drop that.");
        }

    }

    /** Updates the player's inventory and prints the use message if the item is a valid option.
     *  Prints that the player can't use it if it's not possible.
     *
     * @param desiredNoun the item the player inputs to use.
     */
    private static void useCommand(String desiredNoun) {
        boolean found = false;

        for(Item item: Player.getCurrentItems()) {
            if (item.getName().equalsIgnoreCase(desiredNoun)){
                System.out.println(item.getUse());
                Player.getCurrentItems().remove(item);
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("I can't use " + desiredNoun);
        }
    }

    /** Prints the NPC in the room's message if they are in the room.
     *
     * @param desiredNoun the person the player inputs to talk to.
     */
    private static void talkCommand(String desiredNoun) {
        boolean found = false;

        for(Npc npc: Player.getCurrentRoom().getNpc()) {
            if (npc.getName().equalsIgnoreCase(desiredNoun)){
                System.out.println(npc.getMessage());
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("I can't talk to " + desiredNoun);
        }
    }

    /** Handles the duel function in the game.
     *
     * @param desiredNoun the person the player inputs to duel.
     */
    private static void duelCommand(String[] userInput, String desiredNoun) {
        boolean found = false;
        for(String monster : Player.getCurrentRoom().getMonstersInRoom()) {
            if(desiredNoun.equalsIgnoreCase(monster)) {
                found = true;
                Player.setIsInDuel(true);
                DuelInput.interpretInput(userInput);
            }
        }

        if(!found) {
            System.out.println("I can't duel " + desiredNoun);
        }
    }

}
