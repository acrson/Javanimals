import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class Tester
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the cat's name: ");
        String catName = scanner.nextLine();
        System.out.println("Enter the breed for cat(e.g., Small, Medium, Large): ");
        String breedCat = scanner.nextLine();

        System.out.println("Enter the fish's name: ");
        String fishName = scanner.nextLine();
        System.out.println("Enter the breed for fish(e.g., Small, Medium, Large): ");
        String breedFish = scanner.nextLine();

        //create the cat and fish object with initial values
        Cat myCat = new Cat(catName, "Cat", 8, 9, 3, 15, Color.GRAY, 'M',  breedCat);
        Fish myFish = new Fish(fishName, "Fish", 7, 6, 1, 3, Color.GRAY, 'F', breedFish);

        //create a list of pets and add the cats
        List<Pet> petList = new ArrayList<>();  // Correctly typed as List<Pet>
        petList.add(myCat);
        petList.add(myFish);

        //create the PetManager, which will manage the timers for all pets
        PetTimer petTimer = new PetTimer(petList);
        //Start the timer to automatically update pet attributes every 10 seconds
        Timer timer = new Timer(true);//A daemon timer so it will stop when the main program stops
        timer.scheduleAtFixedRate(petTimer, 30000, 30000);//Update every 30-seconds with 30-second delay

        //print initial state of the pets
        System.out.println("Initial state of the pets:");
        for (Pet pet : petList) {
            System.out.println(pet);
        }

        //action for playing or cleaning litter box to user
        while (true) {
            //ask the user what they want to do
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Clean the litter box for "+ catName);
            System.out.println("2. Play with " + catName);
            System.out.println("3. Clean the tank for " + fishName);
            System.out.println("4. Play with " + fishName);
            System.out.print("Enter 1, 2, 3, or 4: \n");

            int choice = scanner.nextInt();

            //Execute user choice
            switch (choice) {
                case 1 -> {
                    if (myCat.getIsAlive()) {
                        myCat.cleanLitterBox();
                    } else {
                        System.out.println("Whiskers is dead and cannot clean the litter box.");
                    }
                }
                case 2 -> {
                    if (myCat.getIsAlive()) {
                        myCat.play();
                    } else {
                        System.out.println("Whiskers is dead and cannot play.");
                    }
                }
                case 3 -> {
                    if (myFish.getIsAlive()) {
                        myFish.cleanTank();
                    } else {
                        System.out.println("Blue is dead and cannot clean the tank.");
                    }
                }
                case 4 -> {
                    if (myFish.getIsAlive()) {
                        myFish.play();
                    } else {
                        System.out.println("Blue is dead and cannot play.");
                    }
                }
                default -> {
                    System.out.println("Exiting program.");
                    return;//Exits the main method and ends the program
                }
            }

            //print the updated state of the cats after action
            for (Pet pet : petList) {
                System.out.println(pet);
            }
        }
    }
}