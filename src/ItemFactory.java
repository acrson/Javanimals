import java.util.Arrays;
import java.util.List;

public class ItemFactory
{
    //added more items
    public static List<Item> createItems()
    {
        return Arrays.asList(
                new Item("Chew Toy", 8, 2, List.of("Dog", "Bird")),
                new Item("Bone", 10, 3, List.of("Dog")),
                new Item("Ball", 3, 1, List.of("Dog")),

                new Item("Scratching Post", 20, 4, List.of("Cat")),
                new Item("Catnip", 8, 2, List.of("Cat")),

                new Item("Bird Swing", 12, 7, List.of("Bird")),
                new Item("Seeds", 5, 1, List.of("Bird")),

                new Item("Fish Toys", 10, 3, List.of("Fish")),
                new Item("Fish Tank Plant", 6, 6, List.of("Fish")),
                new Item("Jumping Hoops", 3, 6, List.of("Fish"))
        );
    }
}
