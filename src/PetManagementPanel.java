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
    boolean uniqueName = true;
    Color Brown = new Color(100, 60, 25);
    Color Beige = new Color(207, 185, 151);

    String dogimg = "\n\n  / \\__     \n (    @\\__  \n /         O\n/   (_____/ \n/_____/   U\n\n\n";
    String catimg = "\n\n\n /\\_/\\ \n( o.o )\n > ^ < \n\n\n";
    String birdimg = "\n\n\n  .--.   \n <(o )___\n  (   ._> \n `----'   \n\n\n";
    String fishimg = "\n\n\n\n><(((('>\n\n\n";

    // Constructor
    public PetManagementPanel(PetDisplayPanel petDisplayPanel, PetDetailsPanel petDetailsPanel) {
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
        JButton[] DetailsButtons = new JButton[MAX_PETS];

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
        JComboBox<String> colorComboBox = new JComboBox<>(new String[]{"Red","Orange","Yellow","Green","Cyan",
                "Blue","Magenta","Pink","Light Gray","Gray","Dark Gray","Black","Brown","Beige"});
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
                uniqueName = true;
                String name = nameField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                Color color = getColorFromName((String) colorComboBox.getSelectedItem());
                char sex = (Character) sexComboBox.getSelectedItem();

                // Default attributes for simplicity
                int health = 10;
                int attention = 10;
                int currentAge = 1;
                int lifeExpectancy = 10;

                System.out.println(petCount);

                // Checks whether the created pet's name already exists in the game
                if (petCount > 0) {
                    for (int i = 0; i < petCount; i++) {
                        if (name.equals(pets[i].getName())) {
                            uniqueName = false;
                            break;
                        }
                    }
                }

                Pet newPet = null;

                // Create the appropriate type of pet
                switch (type) {
                    case "Dog":
                        if (dogCount < MAX_PETS) {
                            newPet = new Dog("Unknown Breed", name, "Dog", attention, health, currentAge, lifeExpectancy, color, sex);
                            //dogs[dogCount++] = (Dog) newPet; // Add dog to array and increment count
                        }
                        break;
                    case "Cat":
                        if (catCount < MAX_PETS) {
                            newPet = new Cat(name, "Cat", attention, health, currentAge, lifeExpectancy, color, sex, "Unknown Breed");
                            //cats[catCount++] = (Cat) newPet; // Add cat to array and increment count
                        }
                        break;
                    case "Bird":
                        if (birdCount < MAX_PETS) {
                            newPet = new Bird("Unknown Breed", name, "Bird", attention, health, currentAge, lifeExpectancy, color, sex);
                            //birds[birdCount++] = (Bird) newPet; // Add bird to array and increment count
                        }
                        break;
                    case "Fish":
                        if (fishCount < MAX_PETS) {
                            newPet = new Fish(name, "Fish", attention, health, currentAge, lifeExpectancy, color, sex, "Unknown Breed");
                            //fish[fishCount++] = (Fish) newPet; // Add fish to array and increment count
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid pet type.");
                        return;
                }

                // Go on with creating the new pet, only if there IS a pet, you have less than 4 pets, and the pet has a unique name.
                if (newPet != null && petCount < MAX_PETS && uniqueName) {

                    pets[petCount++] = newPet; // Add to the main pets array
                    newPet.ID = petCount; // Assign the proper ID number to the pet (first pet gets ID of 1) to keep track

                    // Create a new panel for the pet and display its name
                    JPanel petPanel = new JPanel();
                    petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));  // Stacks components vertically
                    petPanel.setPreferredSize(new Dimension(262, 242));
                    petPanel.setBackground(newPet.getColor());

                    JLabel petNameLabel = new JLabel(newPet.getName());

                    JLabel petImageLabel = new JLabel("n/a");

                    switch (newPet.getType()) {
                        case "Dog":
                            String formattedDogImg = "<html><div style='text-align: center; font-family: monospace;'>" +
                                    dogimg.replace(" ", "&nbsp;")
                                            .replace("<", "&lt;") // Escape the < character
                                            .replace("\n", "<br>") +
                                    "</div></html>";
                            petImageLabel = new JLabel(formattedDogImg);
                            break;
                        case "Cat":
                            String formattedCatImg = "<html><div style='text-align: center; font-family: monospace;'>" +
                                    catimg.replace(" ", "&nbsp;")
                                            .replace("<", "&lt;") // Escape the < character
                                            .replace("\n", "<br>") +
                                    "</div></html>";
                            petImageLabel = new JLabel(formattedCatImg);
                            break;
                        case "Bird":
                            String formattedBirdImg = "<html><div style='text-align: center; font-family: monospace;'>" +
                                    birdimg.replace(" ", "&nbsp;")
                                            .replace("<", "&lt;") // Escape the < character
                                            .replace("\n", "<br>") +
                                    "</div></html>";
                            petImageLabel = new JLabel(formattedBirdImg);
                            break;
                        case "Fish":
                            String formattedFishImg = "<html><div style='text-align: center; font-family: monospace;'>" +
                                    fishimg.replace(" ", "&nbsp;")
                                            .replace("<", "&lt;") // Escape the < character
                                            .replace("\n", "<br>") +
                                    "</div></html>";
                            petImageLabel = new JLabel(formattedFishImg);
                            break;
                    }

                    if (newPet.getColor() == Color.BLACK || newPet.getColor() == Color.blue
                            || newPet.getColor() == Brown || newPet.getColor() == Color.darkGray)
                    {
                        petNameLabel.setForeground(Color.white);
                        petImageLabel.setForeground(Color.white);
                    }

                    // Add a button to view pet details
                    DetailsButtons[petCount-1] = new JButton("View Details");
                    DetailsButtons[petCount-1].setAlignmentX(Component.CENTER_ALIGNMENT);

                    int currentPetIndex = petCount - 1;
                    DetailsButtons[currentPetIndex].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            petDetailsPanel.showPetDetails(pets[currentPetIndex]); // Use the captured index
                        }
                    });


                    petNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    petImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    petNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                    petPanel.add(petNameLabel, BorderLayout.CENTER);
                    petPanel.add(petImageLabel, BorderLayout.CENTER);
                    petPanel.add(DetailsButtons[petCount-1], BorderLayout.SOUTH);

                    // Add the new pet panel to the PetDisplayPanel
                    petDisplayPanel.addPetPanel(petPanel);

                    System.out.println(petCount);

                    JOptionPane.showMessageDialog(null, name + " the " + type + " has been created!");
                    System.out.println("All pets: " + getAllPets());
                }
                else if (petCount == MAX_PETS) {
                    JOptionPane.showMessageDialog(null, "Maximum number of pets reached!");
                }
                else if (!uniqueName) {
                    JOptionPane.showMessageDialog(null, "Pet with name \"" + name + "\" already exists!");
                }
            }
        });
    }

    // Helper method to convert color names to Color objects
    private Color getColorFromName(String colorName) {
        switch (colorName) {
            case "Red":
                return Color.RED;
            case "Orange":
                return Color.ORANGE;
            case "Yellow":
                return Color.YELLOW;
            case "Green":
                return Color.GREEN;
            case "Cyan":
                return Color.CYAN;
            case "Blue":
                return Color.BLUE;
            case "Magenta":
                return Color.MAGENTA;
            case "Pink":
                return Color.PINK;
            case "Light Gray":
                return Color.lightGray;
            case "Gray":
                return Color.GRAY;
            case "Dark Gray":
                return Color.darkGray;
            case "Black":
               return Color.black;
            case "Brown":
                return Brown;
            case "Beige":
                return Beige;
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
