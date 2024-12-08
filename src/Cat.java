import java.awt.*;

public class Cat extends Pet {
    private int litterMeter;
    private final String breed;

    //Constructor for Cats
    public Cat(String name, String type, int attention, int health, int currentAge, int lifeExpectancy,
               Color color, char sex, String breed) {
        super(name, type, health, attention, currentAge, lifeExpectancy, color, sex);
        this.litterMeter = 10; // full meter
        this.breed = breed;
    }
    //Method to decrease litter meter
    public void useLitterBox() {
        if (!getIsAlive()) {
            System.out.println("This pet is dead and cannot use the litter box.");
            return;
        }
        litterMeter -= 1;
        if (litterMeter < 0) {
            litterMeter = 0;//Prevent negative litterMeter
        }
        System.out.println("After using the litter box, LitterMeter is now: " + litterMeter);
        decreaseHealthAndAttention();//Reduce health and attention
    }
    //Clean litter box will restore litter meter and increase happiness
    public void cleanLitterBox() {
        if (!getIsAlive()) {
            System.out.println("This pet is dead and cannot clean the litter box.");
            return;
        }
        litterMeter = 10;//Restore to full meter
        System.out.println("After cleaning the litter box, LitterMeter is now: " + litterMeter);
        increaseAttention(3);// Increase attention and health
    }
    //Playing with the cat increases happiness
    public void play() {
        if (!getIsAlive()) {
            System.out.println("This pet is dead and cannot play.");
            return;
        }
        increaseAttention(3);
    }
    @Override
    public String toString() {
        return super.toString() + ", breed = " + breed + ", litter meter = " + litterMeter;
    }
}

