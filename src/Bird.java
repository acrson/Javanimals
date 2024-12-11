import java.util.Timer;
import java.awt.*;

public class Bird extends Pet{
    private int flymeter;
    private final String breed;
    private Timer timer;

    public Bird(String breed, String name, String type, int attention, int health, int currentAge, int lifeExpectancy, Color color, char sex){
        super(name, type, health, attention, currentAge, lifeExpectancy, color, sex);
        this.flymeter = 10;
        this.breed = breed;
    }

    public void flyBird(){
        if (!getIsAlive()){
            System.out.println("Bird is dead");
            return;
        }
        flymeter -= 1;
        if (flymeter < 0){
            flymeter = 0;
        }
        System.out.println("Bird must fly around, flymeter is now: " + flymeter);
        //decreaseHealth();
    }

    public void flyingBird(){
        if (!getIsAlive()){
            System.out.println("Bird is dead");
            return;
        }
        flymeter = 10;
        System.out.println("After flying bird, flymeter is now: " + flymeter);
        increaseHealth(3);
    }

    public void play(){
        if (!getIsAlive()){
            System.out.println("Bird is dead");
            return;
        }
        increaseHealth(3);
    }

    public String toString() {
        return super.toString() + "breed = " + breed + " flymeter = " + flymeter;
    }
}