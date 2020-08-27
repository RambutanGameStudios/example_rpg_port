import java.util.*;

public class Play {

    static void showInstructions() {
        System.out.println("RPG Game\n========\nCommands:\ngo [direction]\nget [item]");
    }
    static void showStatus(String room,HashMap<String,String> currentRoom,List inventory) {
        System.out.println("---------------------------");
        System.out.println("You are in the "+room);
        System.out.println("Current Inventory: "+inventory);
        System.out.println("Items in Room: "+currentRoom.get("item"));

    }

    static void fight() {
        System.out.println("You fight guard");
    }

    public static void main(String[] args) {
        HashMap<String, HashMap<String, String>> rooms = new HashMap<String, HashMap<String, String>>();
        List<String> inventory = new ArrayList<>();

        HashMap<String, String> hall = new HashMap<String, String>();

        hall.put("south", "kitchen");
        hall.put("east", "diningRoom");
        hall.put("west", "dungeon");
        hall.put("item", "key");

        HashMap<String, String> dungeon = new HashMap<String, String>();
        dungeon.put("east", "hall");
        dungeon.put("item", "axe");

        HashMap<String, String> kitchen = new HashMap<String, String>();
        kitchen.put("north", "hall");
        kitchen.put("item", "monster");

        HashMap<String, String> diningRoom = new HashMap<String, String>();
        diningRoom.put("west","hall");
        diningRoom.put("south","garden");
        diningRoom.put("item", "potion");

        HashMap<String, String> garden = new HashMap<String, String>();
        garden.put("north", "diningRoom");


        rooms.put("hall",hall);
        rooms.put("dungeon",dungeon);
        rooms.put("kitchen",kitchen);
        rooms.put("diningRoom",diningRoom);
        rooms.put("garden",garden);

        String currentRoom = "hall";
        showInstructions();

        Scanner readin = new Scanner(System.in);  // Create a Scanner object
        while(true) {
            showStatus(currentRoom,rooms.get(currentRoom), inventory);
            System.out.println("");
            String input = readin.nextLine();
            List<String> cmd = new ArrayList<>();
            cmd = Arrays.asList(input.split(" "));
            //System.out.println(cmd);

            if(cmd.get(0).equals("go")) {
                if (rooms.get(currentRoom).containsKey(cmd.get(1))) {
                    currentRoom = rooms.get(currentRoom).get(cmd.get(1));
                } else {
                    System.out.println("You can't go that way!");
                }
            }

            if(cmd.get(0).equals("get")) {
                if(rooms.get(currentRoom).get("item").equals(cmd.get(1))) {
                    System.out.println("Got "+cmd.get(1));
                    inventory.add(cmd.get(1));
                    rooms.get(currentRoom).replace("item","");
                } else {
                    System.out.println("Item does not exist");
                }
            }
            //System.out.println(currentRoom);
            if(currentRoom.equals("kitchen") && rooms.get(currentRoom).get("item").equals("monster")) {
                System.out.println("A monster suddenly appears!! You reach inside your bag...");
                if(inventory.contains("axe")) {
                    System.out.println(" and pull out an axe!");
                    System.out.println("The monster's head is cleanly severed from it's body and falls into a boiling pot of soup");
                    inventory.add("stew");
                    System.out.println("Monster Brain Stew added to inventory");
                    rooms.get(currentRoom).replace("item","");
                } else {
                    System.out.println("and nothing is in it of use!");
                    System.out.println("You scream as you are dipped into a boilin put of stew");
                    System.out.println("GAME OVER");
                    break;
                }
            }

            if(currentRoom.equals("garden") && inventory.contains("key")) {
                System.out.println("You have escaped the house!");
                break;
            }



        }


    }
}
