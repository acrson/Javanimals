import java.util.Arrays;
import java.util.List;

public class ItemFactory
{
    public static List<Item> createItems()
    {
        return Arrays.asList(
                new Item("Chew Toy", 10, 6, List.of("Dog", "Bird")),
                new Item("Bone", 10, 6, List.of("Dog")),
                new Item("Scratching Post", 10, 6, List.of("Cat")),
                new Item("Rope", 10, 6, List.of("Cat", "Dog", "Bird")),
                new Item("Bird Swing", 10, 6, List.of("Bird")),
                new Item("Fish Tank Plant", 10, 6, List.of("Fish")),
                new Item("Jumping Hoops", 10, 6, List.of("Fish"))
        );
    }
}
