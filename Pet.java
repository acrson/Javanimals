import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    public int ID;
    private String name;
    private String type;
    private int health;
    private int attention;
    private int currentAge;
    private int lifeExpectancy;
    private Color color;
    private char sex;
    private boolean isAlive;
    private int amount;
    private Timer attentionDecrementTimer;

    // Constructor for Pet
    public Pet(String name, String type, int health, int attention, int currentAge,
               int lifeExpectancy, Color color, char sex) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.attention = attention;
        this.currentAge = currentAge;
        this.lifeExpectancy = lifeExpectancy;
        this.color = color;
        this.sex = sex;
        this.isAlive = true;

        startAttentionDecrementTimer();
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
    public boolean getIsAlive() {
        return isAlive;
    }
    public int getAttention() {return attention;}
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }
    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setAttention(int attention) {
        this.attention += amount; //returns amount of attention
    }

    //Increases Attention meter(changed since we will only have one meter)
    protected void increaseAttention(int amount)
    {
        //only will run if health is = or below 10(doesn't go below meter)
        if (health <= 10) {
            health = Math.max(health + 1, 0);//decreases happiness, ensure it doesn't go below 0
            attention = Math.max(attention + 2, 0);//decreases health, ensure it doesn't go below 0
            System.out.println("Health: " + health + ", Attention: " + attention);
            //Update isAlive based on health
            if (health <= 0) {
                isAlive = false;
                System.out.println(name + " has passed away.");
            }
        }
    }
    protected void decreaseAttention() {
        health = Math.max(health - 1, 0);//decreases happiness, ensure it doesn't go below 0
        attention = Math.max(attention - 2, 0);//decreases health, ensure it doesn't go below 0
        System.out.println("Health: " + health + ", Attention: " + attention);
        //Update isAlive based on health
        if (attention <= 0) {
            isAlive = false;
            System.out.println(name + " has passed away.");
        }
    }

    // Starts the timer to decrease attention every 30 seconds
    private void startAttentionDecrementTimer() {
        attentionDecrementTimer = new Timer(true); //timer stops when program ends
        attentionDecrementTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isAlive) {
                    decreaseAttention(1);
                } else {
                    attentionDecrementTimer.cancel(); // Stop timer if the pet is not alive
                }
            }
        }, 0, 30 * 1000); // 30 seconds interval
    }

    // Stops the attention decrement timer
    public void stopAttentionDecrementTimer() {
        if (attentionDecrementTimer != null) {
            attentionDecrementTimer.cancel();
        }
    }

    //toString method for Pet
    @Override
    public String toString() {
        return "Pet(" + "name = " + getName() + ", type = " + getType()
                + ", health = " + getHealth() + ", current age = " + getCurrentAge() + ", life expectancy = "
                + getLifeExpectancy() + ", color = " + getColor() + ", sex = " + getSex() + ", is alive = " + isAlive + ")";
    }
    //Driver Class
    public static class Test
    {
        public static void main(String[] args)
        {
            // Array of Pet objects
            Pet[] pets = new Pet[4];

            pets[0] = new Pet("Spencer", "dog", 3, 10, 9,
                    13, Color.ORANGE, 'M');
            pets[1] = new Pet("Clover", "cat", 2, 5,
                    16, 20,  Color.GRAY, 'F');
            pets[2] = new Pet("Polly", "bird", 5, 10,
                    14, 25, Color.BLUE, 'F');
            pets[3] = new Pet("Swim-Swim", "fish", 5, 1,
                    4, 5, Color.YELLOW, 'M');

            //Loop to print each Plant object
            for (Pet p : pets) {
                System.out.println(p.toString());
            }
        }
    }
}

