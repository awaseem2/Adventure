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
                        UtilityFunctions.itemsAsString(
                                Map.getLayout().getPlayer().getCurrentItems()));
                break;

            case "use":
                useCommand(desiredNoun);
                break;

            case "talk":
                talkCommand(desiredNoun);
                break;

            case "playerinfo":
                playerinfoCommand();
                break;

            case "duel":
                duelCommand(desiredNoun);
                break;

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
     * @param desiredDirection the place the player inputs to go to.
     */
    private static void goCommand(String desiredDirection) {
        if(Map.getLayout().getPlayer().getCurrentRoom().getMonstersInRoom().isEmpty()) {
            boolean found = false;
            ArrayList<Direction> availableDirections =
                    Map.getLayout().getPlayer().getCurrentRoom().getDirections();

            for (Direction currentDirection : availableDirections) {
                if (currentDirection.getName().equalsIgnoreCase(desiredDirection)) {
                    found = true;
                    Room newRoom = UtilityFunctions.convertToRoom(currentDirection.getRoom());
                    Map.getLayout().getPlayer().setCurrentRoom(newRoom);
                    break;
                }
            }

            if (!found) {
                System.out.println("I can’t go " + desiredDirection);
            }
        } else {
            System.out.println("There are still monsters here, I can’t move.");
        }

    }

    /** Updates the player's inventory and removes it from the room if the item is a valid option.
     *  Prints that the player can't take it if it's not possible.
     *
     * @param desiredItem the item the player inputs to take.
     */
    private static void takeCommand(String desiredItem) {
        if(Map.getLayout().getPlayer().getCurrentRoom().getMonstersInRoom().isEmpty()) {
            boolean found = false;

            for (Item item : Map.getLayout().getPlayer().getCurrentRoom().getItems()) {
                if (item.getName().equalsIgnoreCase(desiredItem)) {
                    Map.getLayout().getPlayer().getCurrentItems().add(item);
                    Map.getLayout().getPlayer().getCurrentRoom().getItems().remove(item);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("I can't take " + desiredItem);
            }

        } else {
            System.out.println("There are still monsters here, I can’t take that.");
        }

    }

    /** Updates the player's inventory and adds it to the room if the item is a valid option.
     *  Prints that the player can't drop it if it's not possible.
     *
     * @param desiredItem the item the player inputs to drop.
     */
    private static void dropCommand(String desiredItem) {
        boolean found = false;

        for (Item item : Map.getLayout().getPlayer().getCurrentItems()) {
            if (item.getName().equalsIgnoreCase(desiredItem)) {
                Map.getLayout().getPlayer().getCurrentItems().remove(item);
                Map.getLayout().getPlayer().getCurrentRoom().getItems().add(item);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("I can't drop " + desiredItem);
        }

    }

    /** Updates the player's inventory and prints the use message if the item is a valid option.
     *  Prints that the player can't use it if it's not possible.
     *
     * @param desiredItem the item the player inputs to use.
     */
    private static void useCommand(String desiredItem) {
        boolean found = false;

        for(Item item: Map.getLayout().getPlayer().getCurrentItems()) {
            if (item.getName().equalsIgnoreCase(desiredItem)){
                System.out.println(item.getUse());
                Map.getLayout().getPlayer().getCurrentItems().remove(item);
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("I can't use " + desiredItem);
        }
    }

    /** Prints the NPC in the room's message if they are in the room.
     *
     * @param desiredPerson the person the player inputs to talk to.
     */
    private static void talkCommand(String desiredPerson) {
        boolean found = false;

        for(Npc npc: Map.getLayout().getPlayer().getCurrentRoom().getNpc()) {
            if (npc.getName().equalsIgnoreCase(desiredPerson)){
                System.out.println(npc.getMessage());
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("I can't talk to " + desiredPerson);
        }
    }

    /** Handles the duel function in the game.
     *
     * @param desiredMonster the person the player inputs to duel.
     */
    private static void duelCommand(String desiredMonster) {
        boolean found = false;
        for(String monster : Map.getLayout().getPlayer().getCurrentRoom().getMonstersInRoom()) {
            if(desiredMonster.equalsIgnoreCase(monster)) {
                found = true;
                Map.getLayout().getPlayer().setIsInDuel(true);

                Monster monsterInBattle = Map.getLayout().getMonsterMap()
                        .get(desiredMonster.toLowerCase());
                System.out.println("You have just entered a duel with "
                        + monsterInBattle.getName());
                System.out.println(monsterInBattle.getEnterDuelMessage());

                Map.getLayout().getPlayer().setCurrentOpponent(monsterInBattle.getName());
            }
        }

        if(!found) {
            System.out.println("I can't duel " + desiredMonster);
        }
    }

    /** Prints the player's level, attack, defense, and health. */
    private static void playerinfoCommand() {
        System.out.println("Here are your stats:");
        System.out.println("[Level]: " + Map.getLayout().getPlayer().getLevel());
        System.out.println("[Attack]:" + Map.getLayout().getPlayer().getAttack());
        System.out.println("[Defense]: " + Map.getLayout().getPlayer().getDefense());
        System.out.println("[Health]: " +
                Map.getLayout().getPlayer().getCurrentHealth());
    }

}
