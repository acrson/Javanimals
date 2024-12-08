import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
I changed some things in this PetManagementPanel, once clicking on an item to buy, the shopPanel then asks the
user to write in the name of the pet(before I just had the breed) once it asked that and I would type in the relevant
name it would say the animal did not exist. After tyring to work some things out I realized I might have to
revamp some things in this class. Instead of having separate arrays for the all the animals, I utilized it into a
single map where the key is the pets name. I think changing into a map will be good for efficient management too.
 */

public class PetManagementPanel extends JPanel {
    private static final int MAX_PETS = 4; //Maximum number of pets in the array
    boolean uniqueName = true;
    Color Brown = new Color(100, 60, 25);
    Color Beige = new Color(207, 185, 151);

    String dogimg = "\n\n  / \\__     \n (    @\\__  \n /         O\n/   (_____/ \n/_____/   U\n\n\n";
    String catimg = "\n\n\n /\\_/\\ \n( o.o )\n > ^ < \n\n\n";
    String birdimg = "\n\n\n  .--.   \n <(o )___\n  (   ._> \n `----'   \n\n\n";
    String fishimg = "\n\n\n\n><(((('>\n\n\n";

    //Constructor
    public PetManagementPanel(PetDisplayPanel petDisplayPanel, PetDetailsPanel petDetailsPanel) {
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
        createPetButton.addActionListener(e -> {
            String name = nameField.getText();
            String type = (String) typeComboBox.getSelectedItem();
            Color color = getColorFromName((String) colorComboBox.getSelectedItem());
            char sex = (Character) sexComboBox.getSelectedItem();

            // Check for duplicate names
            if (AnimalGUI.petDatabase.containsKey(name)) {
                JOptionPane.showMessageDialog(this, "Pet with name \"" + name + "\" already exists!");
                return;
            }

            // Enforce max pet limit
            if (AnimalGUI.petDatabase.size() >= MAX_PETS) {
                JOptionPane.showMessageDialog(this, "Maximum number of pets reached!");
                return;
            }

            // Default attributes for simplicity
            int health = 10, attention = 10, currentAge = 1, lifeExpectancy = 10;
            Pet newPet = null;

            // Create the appropriate type of pet
            switch (type) {
                case "Dog":
                    newPet = new Dog("Unknown Breed", name, "Dog", attention, health, currentAge, lifeExpectancy, color, sex);
                    break;
                case "Cat":
                    newPet = new Cat(name, "Cat", attention, health, currentAge, lifeExpectancy, color, sex, "Unknown Breed");
                    break;
                case "Bird":
                    newPet = new Bird("Unknown Breed", name, "Bird", attention, health, currentAge, lifeExpectancy, color, sex);
                    break;
                case "Fish":
                    newPet = new Fish(name, "Fish", attention, health, currentAge, lifeExpectancy, color, sex, "Unknown Breed");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid pet type.");
                    return;
            }

            // Add to pet database and display
            AnimalGUI.petDatabase.put(name, newPet);
            petDisplayPanel.addPetPanel(createPetPanel(newPet, petDetailsPanel));

            JOptionPane.showMessageDialog(this, name + " the " + type + " has been created!");
        });
    }

    // Helper method to create a panel for a pet
    private JPanel createPetPanel(Pet pet, PetDetailsPanel petDetailsPanel) {
        JPanel petPanel = new JPanel();
        petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));
        petPanel.setPreferredSize(new Dimension(262, 242));
        petPanel.setBackground(pet.getColor());

        JLabel petNameLabel = new JLabel(pet.getName());
        JLabel petImageLabel = new JLabel(getPetImage(pet.getType()));

        if (pet.getColor().equals(Color.BLACK) || pet.getColor().equals(Color.BLUE)
                || pet.getColor().equals(Brown) || pet.getColor().equals(Color.DARK_GRAY)) {
            petNameLabel.setForeground(Color.WHITE);
            petImageLabel.setForeground(Color.WHITE);
        }

        JButton detailsButton = new JButton("View Details");
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsButton.addActionListener(e -> petDetailsPanel.showPetDetails(pet));

        petNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        petImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        petNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        petPanel.add(petNameLabel);
        petPanel.add(petImageLabel);
        petPanel.add(detailsButton);

        return petPanel;
    }

    // Helper method to get pet image as HTML
    private String getPetImage(String type) {
        String img = switch (type) {
            case "Dog" -> dogimg;
            case "Cat" -> catimg;
            case "Bird" -> birdimg;
            case "Fish" -> fishimg;
            default -> "";
        };
        return "<html><div style='text-align: center; font-family: monospace;'>" +
                img.replace(" ", "&nbsp;").replace("<", "&lt;").replace("\n", "<br>") +
                "</div></html>";
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
}
