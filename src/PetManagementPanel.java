import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetManagementPanel extends JPanel {
    private static final int MAX_PETS = 4; // Maximum number of pets in the array
    private Pet[] pets; // Array to store all pets
    private Dog[] dogs; // Array to store all dogs
    private Cat[] cats; // Array to store all cats
    private Bird[] birds; // Array to store all birds
    private Fish[] fish; // Array to store all fish
    private int petCount; // Keeps track of the number of pets in the array
    private int dogCount; // Keeps track of the number of dogs
    private int catCount; // Keeps track of the number of cats
    private int birdCount; // Keeps track of the number of birds
    private int fishCount; // Keeps track of the number of fish

    // Constructor
    public PetManagementPanel() {
        pets = new Pet[MAX_PETS];
        dogs = new Dog[MAX_PETS];
        cats = new Cat[MAX_PETS];
        birds = new Bird[MAX_PETS];
        fish = new Fish[MAX_PETS];
        petCount = 0;
        dogCount = 0;
        catCount = 0;
        birdCount = 0;
        fishCount = 0;

        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.lightGray);

        JLabel titleLabel = new JLabel("Pet Management Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Pet Form Components
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Dog", "Cat", "Bird", "Fish"});
        JLabel colorLabel = new JLabel("Color:");
        JComboBox<String> colorComboBox = new JComboBox<>(new String[]{"Orange", "Gray", "Blue", "Yellow"});
        JLabel sexLabel = new JLabel("Sex:");
        JComboBox<Character> sexComboBox = new JComboBox<>(new Character[]{'M', 'F'});

        JButton createPetButton = new JButton("Create Pet");

        // Layout Configuration
        this.setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(typeLabel);
        formPanel.add(typeComboBox);
        formPanel.add(colorLabel);
        formPanel.add(colorComboBox);
        formPanel.add(sexLabel);
        formPanel.add(sexComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createPetButton);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Button Action Listener
        createPetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                Color color = getColorFromName((String) colorComboBox.getSelectedItem());
                char sex = (Character) sexComboBox.getSelectedItem();

                // Default attributes for simplicity
                int health = 10;
                int attention = 10;
                int currentAge = 1;
                int lifeExpectancy = 10;

                Pet newPet = null;

                // Create the appropriate type of pet
                switch (type) {
                    case "Dog":
                        if (dogCount < MAX_PETS) {
                            newPet = new Dog("Unknown Breed", name, "Dog", attention, health, currentAge, lifeExpectancy, color, sex);
                            dogs[dogCount++] = (Dog) newPet; // Add dog to array and increment count
                        }
                        break;
                    case "Cat":
                        if (catCount < MAX_PETS) {
                            newPet = new Cat(name, "Cat", attention, health, currentAge, lifeExpectancy, color, sex, "Unknown Breed");
                            cats[catCount++] = (Cat) newPet; // Add cat to array and increment count
                        }
                        break;
                    case "Bird":
                        if (birdCount < MAX_PETS) {
                            newPet = new Bird("Unknown Breed", name, "Bird", attention, health, currentAge, lifeExpectancy, color, sex);
                            birds[birdCount++] = (Bird) newPet; // Add bird to array and increment count
                        }
                        break;
                    case "Fish":
                        if (fishCount < MAX_PETS) {
                            newPet = new Fish(name, "Fish", attention, health, currentAge, lifeExpectancy, color, sex, "Unknown Breed");
                            fish[fishCount++] = (Fish) newPet; // Add fish to array and increment count
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid pet type.");
                        return;
                }

                if (newPet != null && petCount < MAX_PETS) {
                    pets[petCount++] = newPet; // Add to the main pets array
                    JOptionPane.showMessageDialog(null, name + " the " + type + " has been created!");
                    System.out.println("All pets: " + getAllPets());
                } else {
                    JOptionPane.showMessageDialog(null, "Maximum number of pets reached!");
                }
            }
        });
    }

    // Helper method to convert color names to Color objects
    private Color getColorFromName(String colorName) {
        switch (colorName) {
            case "Orange":
                return Color.ORANGE;
            case "Gray":
                return Color.GRAY;
            case "Blue":
                return Color.BLUE;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }
    }

    // Method to return a string representation of all pets
    private String getAllPets() {
        StringBuilder petList = new StringBuilder();
        for (int i = 0; i < petCount; i++) {
            petList.append(pets[i].toString()).append("\n");
        }
        return petList.toString();
    }
}
