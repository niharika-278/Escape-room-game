import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;

class RiddlePuzzle{
    boolean exit= false;
    boolean correct = false;

    void solve(){
        while(true){
            System.out.println("I vanish the moment you say my name. What am I?");
            System.out.println("Enter a seven letter word.");
            String word = Game.sc.next();
            Game.sc.nextLine();
            if (word.equals("silence") || word.equals("Silence") || word.equals("SILENCE")) {
                System.out.println("Marvellous! You solved the riddle.");
                System.out.println("A small compartment clicks open, revealing a key. Its surface is covered with an intricate, swirling pattern.");
                Game.inventory.add("swirl_key");
                correct = true;
                return;
            } else if (word.equals("no") || word.equals("NO") || word.equals("No")) {
                exit = true;
                return;
            } else {
                System.out.println("Incorrect, try again or type 'no' to exit.");
                if(correct) break;
                if(exit) break;
            }
        }
    }
}

abstract class Room {
    abstract void enter();
    abstract void inspect1();
    abstract void inspect2();
    abstract void inspect3();
    abstract void inspect4();
}

class Room1 extends Room{

    enum View { DOOR, BOOKSHELF, MIRROR, DESK }
    View currentView = View.DOOR;
    Door whiteDoor = new Door("swirl_key");
    RiddlePuzzle rp = new RiddlePuzzle();
    public void enter(){
        System.out.println("You wake to find yourself in an unfamiliar room, the air thick with strange energy, and an eerie silence all around.");
        while(Game.running){
            while(true){
                switch(currentView){
                    case DOOR:
                        inspect1();
                        break;
                    case BOOKSHELF:
                        inspect2();
                        break;
                    case MIRROR:
                        inspect3();
                        break;
                    case DESK:
                        inspect4();
                        break;
                }
            }
        }


    }
    public void inspect1(){
        System.out.println();
        System.out.println("You see a brown door in front of you, a neat bookshelf to your left and a cluttered desk on your right.");
        System.out.println("1. Inspect the door.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if(ch1==1){
            System.out.println();
            System.out.println("A sturdy wooden door with visible grain patterns stands tall.");
            System.out.println("In the center, an intricate puzzle is embedded — small rotating tiles forming a riddle that must be solved to unlock it.");
            System.out.println("1. Try to open the door.");
            System.out.println("2. Solve the riddle.");
            System.out.println("3. Go back.");
            int ch2 = Game.sc.nextInt();
            Game.sc.nextLine();
            if(ch2==1){
                System.out.println();
                System.out.println("The door can't be opened.");
                currentView = View.DOOR;
            }
            else if(ch2==2) rp.solve();
            else if(ch2==3) currentView = View.DOOR;

            else{
                System.out.println("Invalid input");
                currentView = View.DOOR;

            }
        } else if(ch1==2){
            currentView = View.BOOKSHELF;

        } else if(ch1==3){
            currentView = View.DESK;

        } else if(ch1 == 7){
            Game.inventory.show();
            return;
        } else if(ch1==0){
            System.out.println();
            System.out.println("You chose silence.");
            System.exit(0);
        }else{
            System.out.println("Invalid Input");
        }
    }

    public void inspect2(){
        System.out.println();
        System.out.println("You see a bookshelf in front of you, a mirror and a white door to your left and a brown door to your right.");
        System.out.println("1. Inspect the bookshelf.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if(ch1==1){
            System.out.println();
            System.out.println("A tall bookshelf stands against the wall, its books arranged with almost unnatural neatness and a potted fern sits quietly beside the bookshelf. ");
            System.out.println("At first glance it seems ordinary, but something feels off—one book sits ever so slightly out of place.");
            System.out.println();
            System.out.println("1. Pick the misplaced book.");
            System.out.println("2. Pick a random book.");
            System.out.println("3. Go back.");
            int ch2 = Game.sc.nextInt();
            Game.sc.nextLine();
            if(ch2==1){
                System.out.println();
                System.out.println("The book is titled 'Quiet Hours'. Inside, all the pages are blank.");
                currentView = View.BOOKSHELF;

            }else if(ch2==2){
                System.out.println();
                String book = randomBookPicker();
                System.out.println("You picked "+ book + ". It looks as usual with no clues in it.");
                currentView = View.BOOKSHELF;

            }
            else if(ch2==3) currentView = View.BOOKSHELF;
            else{
                System.out.println("Invalid input");
                currentView = View.BOOKSHELF;

            }
        }else if(ch1==2){
            currentView = View.MIRROR;

        } else if(ch1==3){
            currentView = View.DOOR;

        }else if(ch1 == 7) {
            Game.inventory.show();
            return;
        }else if(ch1==0){
            System.out.println();
            System.out.println("You chose silence.");
            System.exit(0);
        } else{
            System.out.println("Invalid Input");
        }

    }
    public void inspect3() {
        System.out.println();
        System.out.println("You see a mirror and a white door in front of you, a desk to your left and a bookshelf to your right.");
        System.out.println("1. Move closer.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if (ch1 == 1) {
            System.out.println();
            System.out.println("A plain white door stands opposite, with a swirling pattern engraved on the doorknob and its surface marked with deep scratches. And a mirror hangs next to it, reflecting you.");
            System.out.println("1. Try to open the door.");
            System.out.println("2. Move closer to the mirror.");
            System.out.println("3. Go back.");
            int ch2 = Game.sc.nextInt();
            Game.sc.nextLine();
            if (ch2 == 1) {
                System.out.println();
                if (whiteDoor.tryUnlock(Game.inventory)) {
                    System.out.println("Door opens with a soft click, into another room.");
                    Game.currentRoom = new Room2();
                    return;
                } else {
                    System.out.println();
                    System.out.println("The door is locked.");
                    currentView = View.MIRROR;

                }

            } else if (ch2 == 2) {
                System.out.println();
                System.out.println("The mirror reflects you clearly at first glance, but on closer inspection, faint words emerge: “Say nothing.” ");
                currentView = View.MIRROR;

            } else if (ch2 == 3) currentView = View.MIRROR;
            else {
                System.out.println("Invalid input");
                currentView = View.MIRROR;

            }
        } else if (ch1 == 2) {
            currentView = View.DESK;

        } else if (ch1 == 3) {
            currentView = View.BOOKSHELF;

        } else if (ch1 == 7) {
            Game.inventory.show();
            return;
        } else if(ch1==0){
            System.out.println();
            System.out.println("You chose silence.");
            System.exit(0);
        } else {
            System.out.println("Invalid Input");
        }
    }
    public void inspect4(){
        System.out.println();
        System.out.println("You see a cluttered desk in front of you, a brown door to your left and a mirror and a white door to your right.");
        System.out.println("1. Inspect the desk.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if(ch1==1){
            System.out.println("You see a wooden desk near the window, an armchair pulled slightly aside, a dustbin tucked below, a picture frame on the wall, a creeping plant trailing along the corner, and a glass showcase.");
            System.out.println("1. Move closer to desk and inspect.");
            System.out.println("2. Move closer to the picture frame on the wall.");
            System.out.println("3. Move closer to the showcase.");
            System.out.println("4. Go back.");
            int ch2 = Game.sc.nextInt();
            Game.sc.nextLine();
            if(ch2==1){
                System.out.println();
                System.out.println("The desk is littered with open diaries and scattered pens. A yellow sticky note reads “listen carefully,” lying beside a small globe and a copper water bottle. Outside the window, the world seems completely still.");
                currentView = View.DESK;

            }else if(ch2==2){
                System.out.println();
                System.out.println("The picture shows a young man wearing glasses, playfully hugging a young woman with short hair from behind. Both are laughing and looking at the camera. An engraving at the bottom reads: “…is loudest when unspoken.”");
                currentView = View.DESK;

            }else if(ch2==3){
                System.out.println();
                System.out.println("Behind the glass, neatly arranged trophies, medals, and awards catch the light, interspersed with a few small ceramic artifacts and keepsakes.");
                currentView = View.DESK;

            }
            else if(ch2==4) currentView = View.DESK;
            else{
                System.out.println("Invalid input");
                currentView = View.DESK;

            }
        }else if(ch1==2){
            currentView = View.DOOR;

        } else if(ch1==3){
            currentView = View.MIRROR;

        } else if(ch1 == 7) {
            Game.inventory.show();
            return;
        } else if(ch1==0){
            System.out.println("You chose silence.");
            System.exit(0);
        }else{
            System.out.println("Invalid Input");
        }

    }
    String randomBookPicker(){
        Random rand = new Random();
        int i = rand.nextInt(7);
        String[] book = new String[7];
        book = new String[]{"Pippi Longstocking by Astrid Lindgren", "The Bell Jar by Sylvia Plath", "The Handmaid's Tale by Margaret Atwood", "Frankenstein by Mary Shelley", "Emma by Jane Austen", "Orlando by Virginia Woolf", "Jane Eyre by Charlotte Bronte"};
        return book[i];
    }

}

class Room2 extends Room{
    int count=0;
    Room2(){
        this.enter();
    }
    enum View { GLASS, SCRIBBLE, TABLE, DOOR }
    View currentView = View.DOOR;
    Door silentDoor = new Door("do_nothing");

    public void enter(){
        System.out.println();
        System.out.println("You step into a dimly lit room that feels crowded despite its emptiness.");
        System.out.println("Glass glints from one wall, chalk marks stain another, and a desk sits buried under scattered notes.");
        System.out.println("Opposite you stands a plain door.");
        System.out.println("Something about the room feels… dishonest.");
        while(Game.running){
            while(true){
                switch(currentView){
                    case DOOR:
                        inspect1();
                        break;
                    case TABLE:
                        inspect2();
                        break;
                    case GLASS:
                        inspect3();
                        break;
                    case SCRIBBLE:
                        inspect4();
                        break;
                }
            }
        }


    }

    public void inspect1(){
        System.out.println();
        System.out.println("There is a door infront of you, a messy table on your left and a wall with chalk scribbles on your right.");
        System.out.println("1. Move closer to the door.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if(ch1==1){
            System.out.println();
            System.out.println("A plain door stands opposite with no handle markings, no lock, no symbols.");
            System.out.println("Only a faint sentence is carved into the surface: “When nothing is chosen, everything aligns.”");
            if(count<3) {
                System.out.println("1. Try to open the door.");
                System.out.println("3. Go back.");
                int ch2 = Game.sc.nextInt();
                Game.sc.nextLine();
                count++;
                if (ch2 == 1) {
                    System.out.println();
                    System.out.println("Door can't be opened!");

                } else if (ch2 == 3) currentView = View.DOOR;
                else {
                    System.out.println();
                    System.out.println("Invalid input");
                    currentView = View.DOOR;

                }
            }
            else{
                System.out.println("1. Try to open the door.");
                System.out.println("2. Do nothing.");
                System.out.println("3. Go back.");
                int ch2 = Game.sc.nextInt();
                Game.sc.nextLine();
                if (ch2 == 2 || ch2 == 1 ) {
                    if(Game.inventory.items.isEmpty()){
                        System.out.println();
                        System.out.println("The room goes silent. The door opens.");
                        System.out.println("You have successfully escaped!");
                        Game.running = false;
                        System.exit(0);
                    } else {
                        System.out.println();
                        System.out.println("Something is still weighing you down.");
                    }

                } else if (ch2 == 3) currentView = View.DOOR;
                else {
                    System.out.println("Invalid input");
                    currentView = View.DOOR;

                }
            }
        } else if(ch1==2){
            currentView = View.TABLE;

        } else if(ch1==3){
            currentView = View.SCRIBBLE;
        } else if(ch1 == 7){
            Game.inventory.show();
            return;
        }else if(ch1==0){
            System.out.println();
            System.out.println("You chose silence.");
            System.exit(0);
        } else{
            System.out.println("Invalid Input");
        }

    }
    public void inspect2(){
        System.out.println();
        System.out.println("There is a messy table infront of you, a wall with coloured glass pieces on your left and a plain door on your right.");
        System.out.println("1. Inspect the table.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if(ch1==1){
            System.out.println();
            System.out.println("The sits beneath dim light, buried under torn notes, glass shard, smudged ink, and broken stationery.");
            System.out.println("A snapped ruler lies across a page filled with repeated words: “Four walls. Four pieces. Four chances.”");
            System.out.println();
            System.out.println("1. Try to pick up an item.");
            System.out.println("2. Try to pick up the page.");
            System.out.println("3. Empty your inventory on the table.");
            System.out.println("4. Go back.");
            int ch2 = Game.sc.nextInt();
            Game.sc.nextLine();
            if(ch2==1){
                System.out.println();
                this.itemPicker();
                currentView = View.TABLE;
            }
            else if(ch2==2){
                System.out.println();
                Game.inventory.add("Page");
                currentView = View.TABLE;
            }
            else if(ch2==3){
                System.out.println();
                Game.inventory.remove();
                currentView = View.TABLE;
            }
            else if(ch2==4) currentView = View.TABLE;
            else{
                System.out.println();
                System.out.println("Invalid input");
                currentView = View.DOOR;

            }
        } else if(ch1==2){
            currentView = View.GLASS;

        } else if(ch1==3){
            currentView = View.DOOR;
        } else if(ch1 == 7){
            Game.inventory.show();
            return;
        } else if(ch1==0){
            System.out.println();
            System.out.println("You chose silence.");
            System.exit(0);
        } else{
            System.out.println("Invalid Input");
            currentView = View.TABLE;
        }

    }
    public void inspect3(){
        System.out.println();
        System.out.println("The wall infront of you has coloured glass pieces, there is a wall with chalk scribbles on your left and a messy table on your right.");
        System.out.println("1. Move closer to the wall.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if(ch1==1){
            System.out.println();
            System.out.println("Directly in front of you, set into the wall, is a large glass panel.");
            System.out.println("Inside are diamond-shaped glass pieces in red, blue, green, and yellow colours, each resting in a carved slot.");
            System.out.println("One slot is conspicuously empty.");
            System.out.println("A small plaque beneath the panel reads: “Only one completes the whole.”");
            System.out.println();
            System.out.println("1. Try to touch/move/take the glass pieces.");
            System.out.println("2. Go back.");
            int ch2 = Game.sc.nextInt();
            Game.sc.nextLine();
            if(ch2==1){
                System.out.println();
                System.out.println("The moment you touch the glass, a low hum fills the room and the lights begin to flicker rapidly.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("As soon as you pull your hand away, the hum fades and the lights return to normal.");
                currentView = View.GLASS;
            }
            else if(ch2==2){
                currentView = View.GLASS;
            }
            else{
                System.out.println();
                System.out.println("Invalid input");
                currentView = View.DOOR;

            }
        } else if(ch1==2){
            currentView = View.SCRIBBLE;

        } else if(ch1==3){
            currentView = View.TABLE;
        } else if(ch1 == 7){
            Game.inventory.show();
            return;
        } else if(ch1==0){
            System.out.println();
            System.out.println("You chose silence.");
            System.exit(0);
        }else{
            System.out.println("Invalid Input");
            currentView = View.GLASS;
        }

    }
    public void inspect4(){
        System.out.println();
        System.out.println("There is a wall with chalk scribbles infront of you, the door on your left and a wall with glass pieces glinting on your right.");
        System.out.println("1. Move closer to the wall.");
        System.out.println("2. Go to your left.");
        System.out.println("3. Go to your right.");
        System.out.println("7. Open Inventory.");
        System.out.println("0. Sit down and give up.");
        int ch1 = Game.sc.nextInt();
        Game.sc.nextLine();
        if(ch1==1){
            System.out.println();
            System.out.println("The wall is covered in chalk marks with half-erased equations, symbols, and rough sketches.");
            System.out.println("Diamond shapes repeat over and over, drawn at different angles.");
            System.out.println("Between the chaos, faint phrases stand out:");
            System.out.println("LOOK HARDER.");
            System.out.println("COLORS MATTER.");
            System.out.println("COUNT THE PIECES.");
            System.out.println();
            System.out.println("1. Try to write something on the wall.");
            System.out.println("2. Try to erase from the wall.");
            System.out.println("3. Go back.");
            int ch2 = Game.sc.nextInt();
            Game.sc.nextLine();
            if(ch2==1){
                System.out.println();
                System.out.println("Write: ");
                Game.sc.nextLine();
                System.out.println("Nothing happens.");
                currentView = View.SCRIBBLE;
            }
            else if(ch2==2){
                System.out.println();
                System.out.println("Nothing happens.");
                currentView = View.SCRIBBLE;
            }
            else{
                System.out.println();
                System.out.println("Invalid input");
                currentView = View.SCRIBBLE;

            }
        } else if(ch1==2){
            currentView = View.DOOR;

        } else if(ch1==3){
            currentView = View.GLASS;
        } else if(ch1 == 7){
            Game.inventory.show();
            return;
        } else if(ch1==0){
            System.out.println();
            System.out.println("You chose silence.");
            System.exit(0);
        } else{
            System.out.println();
            System.out.println("Invalid Input");
            currentView = View.TABLE;
        }

    }
    void itemPicker(){
        Random rand = new Random();
        int i = rand.nextInt(8);
        String[] items = new String[8];
        items = new String[]{"a yellow note that says 'Four walls'", "a chalk", "a blue note that says 'Wrong order breaks it'", "a shard of mirror that says 'you are trying too hard'", "a red note that says 'all a lie'", "a broken pen", "a fortune cookie slip that says 'Go easy, today :)'", "an empty tube of lip gloss"};
        System.out.println("You picked up "+ items[i] +".");
        Game.inventory.add(items[i]);
    }

}

class Inventory{
     HashSet<String> items = new HashSet<>();
    void add(String item) {
        if(items.add(item)){
            System.out.println( item +" added to inventory.");
        }
    }
    void remove(){
        items.clear();
        System.out.println("Inventory has been emptied!");
    }

    boolean has(String item) {
        return items.contains(item);
    }

    void show() {
        if(items.isEmpty()){
            System.out.println("Inventory is empty.");
        } else{
            System.out.println("Inventory:");
            for(String i: items){
                System.out.println(i);
            }
        }
    }
}
class Door{
    String requiredKey;
    boolean locked = true;

    Door(String requiredKey) {
        this.requiredKey = requiredKey;
    }

    boolean isLocked() {
        return locked;
    }

    boolean tryUnlock(Inventory inventory) {
        if (!locked) return true;

        if (inventory.has(requiredKey)) {
            locked = false;
            return true;
        }
        return false;
    }
}

class Game{
    public static final Scanner sc = new Scanner(System.in);
    static Inventory inventory;
    static boolean running = true;
    Game(){
        inventory = new Inventory();
        System.out.println("Welcome to the Escape Room.");
        System.out.println("The door is sealed. You must use your surroundings to engineer an escape.");
        System.out.println("Instructions: When you enter the number next to the action, the action will take place.");
        System.out.println("Begin when you're ready!");
    }
    
    static Room room1 = new Room1();
    static Room currentRoom = room1;

    public void start(){
        Game.sc.nextLine();
        room1.enter();

    }

}

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }
}
