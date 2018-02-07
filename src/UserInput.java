import java.util.ArrayList;

public class UserInput {
    public static void interpretInput(String[] userInput) {
        String action = userInput[0];
        String parameter = inputAfterAction(userInput);
        Room currentRoom = Player.getCurrentRoom();
        switch(action.toLowerCase()) {
            case "go":
                boolean found = false;
                String desiredDirection = parameter;
                ArrayList<Direction> availableDirections =
                        currentRoom.getDirections();
                for(Direction currentDirection : availableDirections) {
                    if(currentDirection.getName().equalsIgnoreCase(desiredDirection)) {
                        found = true;
                        Room newRoom = UtilityFunctions.convertToRoom(currentDirection.getRoom());
                        Player.setCurrentRoom(newRoom);
                        break;
                    }
                }
                if(!found){
                    System.out.println("I can’t go " + parameter);
                }
                break;
            case "take":
                found = false;
                for(Item item : Player.getCurrentRoom().getItems()) {
                    if (item.getName().equalsIgnoreCase(parameter)) {
                        Player.getCurrentItems().add(item);
                        Player.getCurrentRoom().getItems().remove(item);
                        found = true;
                        break;
                    }
                }

                if(!found) {
                    System.out.println("I can't take " + parameter);
                }
                break;
            case "drop":
                found = false;
                for(Item item: Player.getCurrentItems()) {
                    if (item.getName().equalsIgnoreCase(parameter)) {
                        Player.getCurrentItems().remove(item);
                        currentRoom.getItems().add(item);
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    System.out.println("I can't drop " + parameter);
                }
                break;
            case "list":
                System.out.println("You are carrying " + UtilityFunctions.itemsAsString(Player.getCurrentItems()));
                break;
            case "use":
                found = false;
                for(Item item: Player.getCurrentItems()) {
                    if (item.getName().equalsIgnoreCase(parameter)){
                        System.out.println(item.getUse());
                        Player.getCurrentItems().remove(item);
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    System.out.println("I can't use " + parameter);
                }
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
                    System.out.println("I don't understand '" + action  + " " + parameter + "'");
                }
                break;

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
