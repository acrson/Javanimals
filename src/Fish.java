import java.awt.*;

public class Fish extends Pet
{
    private int tankMeter;
    private final String breed;

    //Constructor for Fish
    public Fish(String name, String type, int attention, int health, int currentAge, int lifeExpectancy,
                Color color, char sex, int difficultyLevel, String breed)
    {
        //Inherit from superclass
        super(name, type, attention, health, currentAge, lifeExpectancy, color, sex, difficultyLevel);
        this.tankMeter = 1;//full meter
        this.breed = breed;
    }
    //Decreases health and happiness every 10 seconds when tank is dirty
    public void dirtyTank()
    {
        tankMeter -= 1;//decrease litter meter by 20%
        if (tankMeter < 0)
        {
            tankMeter = 0;//prevents negative litterMeter
        }
        System.out.println("After using the tank, tankMeter is now: " + tankMeter);
        increaseHealthAttention();//reduces happiness and health
    }
    //Clean the tank, will restore tank and increase happiness
    public void cleanTank()
    {
        System.out.println("After cleaning the tank, tankMeter is now: " + tankMeter);
        increaseHealthAttention();//increase happiness when cleaning the box
    }
    //Play with fish which will increase happiness
    public void play()
    {
        System.out.println("\nAfter playing with the fish:");
        increaseHealthAttention();
        //System.out.println("Attention is now " + getAttention());
    }

}
