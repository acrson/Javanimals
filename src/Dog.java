import java.util.Timer;
import java.awt.*;

public class Dog extends Pet{
    private int walkmeter;
    private String breed;
    private Timer timer;

    public Dog(String breed, String name, String type, int attention, int health, int currentAge, int lifeExpectancy, Color color, char sex){
        super(name, type, health, attention, currentAge, lifeExpectancy, color, sex);
        this.walkmeter = 10;
        this.breed = breed;
    }

    public void walkDog(){
        if (!getIsAlive()){
            System.out.println("Dog is dead");
            return;
        }
        walkmeter -= 1;
        if (walkmeter < 0){
            walkmeter = 0;
        }
        System.out.println("Dog must be walked, walkmeter is now: " + walkmeter);
        decreaseAttention();
    }

    public void walkingDog(){
        if (!getIsAlive()){
            System.out.println("Dog is dead");
            return;
        }
        walkmeter = 10;
        System.out.println("After walking dog, walkmeter is now: " + walkmeter);
        increaseAttention(3);
    }

    public void play(){
        if (!getIsAlive()){
            System.out.println("Dog is dead");
            return;
        }
        increaseAttention(3);
    }

    public String toString() {
        return super.toString() + "breed =  " + breed + " walkmeter = " + walkmeter;
    }
}
