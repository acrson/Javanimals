//bMiranda Nichols, Carson Stell, Camryn Joyner, Craig Crutcher

import java.awt.*;

public class Pet {
    private final String name;
    private final String type;
    private int happiness;
    private int health;
    private int currentAge;
    private final int lifeExpectancy;
    private final Color color;
    private final char sex;
    private final int difficultyLevel;

    // Constructor for Pet
    public Pet(String name, String type, int happiness, int health, int currentAge,
               int lifeExpectancy, Color color, char sex, int difficultyLevel) {
        this.name = name;
        this.type = type;
        this.happiness = happiness;
        this.health = health;
        this.currentAge = currentAge;
        this.lifeExpectancy = lifeExpectancy;
        this.color = color;
        this.sex = sex;
        this.difficultyLevel = difficultyLevel;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHealth() {
        return health;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }

    public Color getColor() {
        return color;
    }

    public char getSex() {
        return sex;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    // toString method for Pet
    @Override
    public String toString() {
        return "Pet(" + "name = " + getName() + ", type = " + getType() + ", happiness = " + getHappiness()
                + ", health = " + getHealth() + ", current age = " + getCurrentAge() + ", life expectancy = "
                + getLifeExpectancy() + ", color = " + getColor() + ", sex = " + getSex() +
                ", difficultyLevel = " + getDifficultyLevel() + ")";
    }

    //Driver Class
    public static class Test
    {
        public static void main(String[] args)
        {
            // Array of Pet objects
            Pet[] pets = new Pet[4];

            pets[0] = new Pet("Spencer", "dog", 93, 82, 9,
                    13, Color.ORANGE, 'M', 3);
            pets[1] = new Pet("Clover", "cat", 23, 95, 5,
                    16, Color.GRAY, 'F', 8);
            pets[2] = new Pet("Polly", "bird", 86, 54, 11,
                    14, Color.BLUE, 'F', 7);
            pets[3] = new Pet("Swim-Swim", "fish", 2, 90, 1,
                    4, Color.YELLOW, 'M', 3);

            //Loop to print each Plant object
            for (Pet p : pets) {
                System.out.println(p.toString());
            }
        }
    }
}