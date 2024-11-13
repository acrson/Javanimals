import java.awt.*;

public class Cat extends Pet
{
    private int litterMeter;
    private final String breed;

    //Constructor for Cats
    public Cat(String name, String type, int attention, int health, int currentAge, int lifeExpectancy,
               Color color, char sex, int difficultyLevel, String Breed)
    {
        //Inherit from superclass
        super(name, type, attention, health, currentAge, lifeExpectancy, color, sex, difficultyLevel);
        this.litterMeter = 10;//full meter
        this.breed = Breed;
    }
    //Method to decrease litter meter
    public void useLitterBox() {
        litterMeter -= 1;
        if (litterMeter < 0)
        {
            litterMeter = 0; // Prevent negative litterMeter
        }
        System.out.println("After using the litter box, LitterMeter is now: " + litterMeter);
        decreaseHealthAttention();  // Reduce happiness and health
    }
    //Clean litter box will restore litter meter and increase happiness
    public void cleanLitterBox()
    {
        if (! isAlive())
        {
            System.out.println("This pet is dead and cannot perform actions.");
            return;
        }
        System.out.println("\n" + "After cleaning the litter box, LitterMeter is now: " + litterMeter);
        increaseHealthAttention();
    }
    //Playing with the cat will increase happiness
    public void play()
    {
        //checks if animal is alive and isn't returns as dead
        if (! isAlive())
        {
            System.out.println("This pet is dead and cannot perform actions.");
            return;
        }
        increaseHealthAttention();
    }

}

