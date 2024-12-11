import java.util.List;

public class Item
{
    //attributes
    private final String name; //name of item
    private final int price; //price of the item
    private final int points; //points gained from using item
    private final List<String> applicableAnimals; //list of animals the item can be used on

    public Item(String name, int price, int points, List<String> applicableAnimals)
    {
        this.name = name;
        this.price = price;
        this.points = points;
        this.applicableAnimals = applicableAnimals;
    }

    //getters
    public String getName() { return name; }
    public int getPrice() {return price;}
    public int getPoints() { return points; }
    public List<String> getApplicableAnimals() {return applicableAnimals;}

    //setters
    public boolean isApplicable(String petType) //checks if item applies to certain petType
    {
        return applicableAnimals.contains(petType);
    }

   // not sure if well use this here but thought id make it just in case
   /*public void applyToPet(Pet pet) //applies points to pets happiness
    {
        if (isApplicable(pet.getClass().getSimpleName()))
        {
            pet.increaseHealth(3);
        }
    }*/

    //example
}