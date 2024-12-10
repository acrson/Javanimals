import java.awt.*;

public class Fish extends Pet {
    private int tankMeter;
    private final String breed;

    //Constructor for Fish
    public Fish(String name, String type, int attention, int health, int currentAge, int lifeExpectancy,
                Color color, char sex, String breed) {
        //Inherit from superclass
        super(name, type, attention, health, currentAge, lifeExpectancy, color, sex);
        this.tankMeter = 1;//full meter
        this.breed = breed;
    }
    //Decreases health and happiness every 10 seconds when tank is dirty
    public void dirtyTank()
    {
        if (!getIsAlive()) {
            System.out.println("This pet is dead and you clean tank.");
            return;
        }
        tankMeter -= 1;
        if (tankMeter < 0) {
            tankMeter = 0;//Prevent negative litterMeter
        }
        System.out.println("After using the tank, it is now: " + tankMeter);
        decreaseAttention();//Reduce health and attention
    }
    //Clean the tank, will restore tank and increase happiness
    public void cleanTank() {
        if (!getIsAlive()) {
            System.out.println("This pet is dead and you cannot clean the tank.");
            return;
        }
        tankMeter = 10;//Restore to full meter
        System.out.println("After cleaning the tank, it is now: " + tankMeter);
        increaseAttention(3); // Increase attention and health
    }
    //Play with fish which will increase happiness
    public void play() {
        if (!getIsAlive()) {
            System.out.println("This pet is dead and cannot play.");
            return;
        }
        increaseAttention(3);
    }
    @Override
    public String toString() {
        return super.toString() + ", breed = " + breed + ", litter meter = " + tankMeter;
    }
}
