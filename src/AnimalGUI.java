import javax.swing.*;
import java.awt.*;

// Serves as the main for GUI
public class AnimalGUI {
    public static PetManagementPanel petManagementPanel;
    public static PetDisplayPanel petDisplayPanel;
    public static PetDetailsPanel petDetailsPanel;
    public static InventoryPanel inventoryPanel;
    public static ShopPanel shopPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Javanimals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(2000, 900));
        mainPanel.setBackground(Color.BLACK);
        Pet pet = new Pet("", "", 0, 0 ,0, 0, Color.BLACK, 'M');

        petDisplayPanel = new PetDisplayPanel();
        petDetailsPanel = new PetDetailsPanel(pet);
        petManagementPanel = new PetManagementPanel(petDisplayPanel, petDetailsPanel);
        inventoryPanel = new InventoryPanel();
        shopPanel = new ShopPanel();

        frame.add(mainPanel, BorderLayout.CENTER);
        petDisplayPanel.setBounds(240, 65, 1100, 280);
        shopPanel.setBounds(1350, 5, 400, 750);
        inventoryPanel.setBounds(240, 5, 1100, 50);
        petDetailsPanel.setBounds(240, 355, 545, 400);
        petManagementPanel.setBounds(795, 355, 545, 400);

        mainPanel.add(petDisplayPanel);
        mainPanel.add(petDetailsPanel);
        mainPanel.add(inventoryPanel);
        mainPanel.add(petManagementPanel);
        mainPanel.add(shopPanel);

        frame.setPreferredSize(new Dimension(2000, 900));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.pack();
        frame.setVisible(true);
    }
}
