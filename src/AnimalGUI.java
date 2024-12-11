import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Serves as the main for GUI
public class AnimalGUI {
    public static PetManagementPanel petManagementPanel;
    public static PetDisplayPanel petDisplayPanel;
    public static PetDetailsPanel petDetailsPanel;
    public static ShopPanel shopPanel;

    //Map my animal inputted names to access them in ShopPanel
    public static Map<String, Pet> petDatabase = new HashMap<>();

    //Sets users points to 0
    public static int userPoints = 0;
    public static void main(String[] args) {

        JFrame frame = new JFrame("Javanimals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(2000, 900));
        mainPanel.setBackground(Color.BLACK);

        //Need to make a map that holds all pets made with names with there items
        List<Item> itemsList = ItemFactory.createItems();
        Map<String, Item> itemsMap = itemsList.stream()
                .collect(Collectors.toMap(Item::getName, item -> item));

        Pet pet = new Pet("", "", 0, 0 ,0, 0, Color.BLACK, 'M');

        petDisplayPanel = new PetDisplayPanel();
        petDetailsPanel = new PetDetailsPanel(pet);
        petManagementPanel = new PetManagementPanel(petDisplayPanel, petDetailsPanel);
        shopPanel = new ShopPanel(itemsMap, userPoints, petDatabase);

        frame.add(mainPanel, BorderLayout.CENTER);
        petDisplayPanel.setBounds(240, 5, 1100, 340);
        shopPanel.setBounds(1350, 5, 400, 750);
        petDetailsPanel.setBounds(240, 355, 545, 400);
        petManagementPanel.setBounds(795, 355, 545, 400);

        mainPanel.add(petDisplayPanel);
        mainPanel.add(petDetailsPanel);
        mainPanel.add(petManagementPanel);
        mainPanel.add(shopPanel);

        frame.setPreferredSize(new Dimension(2000, 900));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.pack();
        frame.setVisible(true);
    }
}
