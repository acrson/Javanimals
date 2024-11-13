//bMiranda Nichols, Carson Stell, Camryn Joyner, Craig Crutcher

import java.awt.*;

public class Pet {
    private final String name;
    private final String type;
    private int health;
    private int currentAge;
    private final int lifeExpectancy;
    private final Color color;
    private final char sex;

    // Constructor for Pet
    public Pet(String name, String type, int health, int currentAge,
               int lifeExpectancy, Color color, char sex) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.currentAge = currentAge;
        this.lifeExpectancy = lifeExpectancy;
        this.color = color;
        this.sex = sex;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public String setName(String name) {
        return name;
    }

    public String setType(String type) {
        return type;
    }

    public int setHealth(int health) {
        return health;
    }

    public int setCurrentAge(int currentAge) {
        return currentAge;
    }

    public int setLifeExpectancy(int lifeExpectancy) {
        return lifeExpectancy;
    }

    public Color setColor(Color color) {
        return color;
    }

    public boolean setSex(boolean sex) {
        return sex;
    }

    // toString method for Pet
    @Override
    public String toString() {
        return "Pet(" + "name = " + getName() + ", type = " + getType() 
                + ", health = " + getHealth() + ", current age = " + getCurrentAge() + ", life expectancy = "
                + getLifeExpectancy() + ", color = " + getColor() + ", sex = " + getSex();
    }

    //Driver Class
    public static class Test
    {
        public static void main(String[] args)
        {
            // Array of Pet objects
            Pet[] pets = new Pet[4];

            pets[0] = new Pet("Spencer", "dog", 93, 9,
                    13, Color.ORANGE, 'M');
            pets[1] = new Pet("Clover", "cat", 23, 5,
                    16, Color.GRAY, 'F');
            pets[2] = new Pet("Polly", "bird", 86, 11,
                    14, Color.BLUE, 'F');
            pets[3] = new Pet("Swim-Swim", "fish", 2, 1,
                    4, Color.YELLOW, 'M');

            //Loop to print each Plant object
            for (Pet p : pets) {
                System.out.println(p.toString());
            }
        }
    }
}