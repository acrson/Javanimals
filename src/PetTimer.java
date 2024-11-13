import java.util.List;
import java.util.TimerTask;

public class PetTimer extends TimerTask
{
    private final List<Pet> petList;

    public PetTimer(List<Pet> petList)
    {
        this.petList = petList;
    }

    @Override
    public void run() {
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);

            // Increase the age of each pet by 1 year (every 10 seconds)
            pet.setCurrentAge(pet.getCurrentAge() + 1);
            System.out.println("\n" + pet.getName() + " has aged by 1 year. Current age: " + pet.getCurrentAge());


            //Simulate litter box usage and tank cleaning or other automated behaviors here
            if (pet instanceof Cat) {
                ((Cat) pet).useLitterBox();
            } else if (pet instanceof Fish) {
                ((Fish) pet).dirtyTank();
            }

            //Check if pet's health has reached zero
            if (pet.getHealth() <= 0) {
                System.out.println("\n" + pet.getName() + " has died from health issues.\n");
                petList.remove(i);
                i--;//Adjusts index after pets removal
            }
            //Check if pet's happiness has reached zero
            if (pet.getAttention() <= 0) {
                System.out.println("\n" + pet.getName() + " has died from unhappiness.\n");
                petList.remove(i);
                i--;//Adjusts index after pets removal
            }

            //Check if pet's currentAge has reached or exceeded its lifeExpectancy
            if (pet.getCurrentAge() >= pet.getLifeExpectancy()) {
                System.out.println(pet.getName() + " has passed away due to old age.");
                petList.remove(i);
                i--;//Adjust index after pets removal
            }
        }
    }
}

