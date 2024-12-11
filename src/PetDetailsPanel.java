import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Objects;

public class PetDetailsPanel extends JPanel {
    private JButton toggleButton;
    private JPanel detailsPanel;
    private boolean isExpanded = false;
    private HashMap<Color, String> colorNames = new HashMap<>(); // Class-level field
    private Timer healthUpdateTimer;

    public PetDetailsPanel(Pet pet) {
        Color skyBlue = new Color(141, 153, 177);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(skyBlue);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pet Details Panel");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        /*
        toggleButton = new JButton("PetDetailsPanel");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isExpanded = !isExpanded;
                detailsPanel.setVisible(isExpanded);
                toggleButton.setText(isExpanded ? "Hide Details" : "Show Details");
            }
        });
        add(toggleButton);*/

        detailsPanel = new JPanel();
        detailsPanel.setBackground(skyBlue);
        detailsPanel.setPreferredSize(new Dimension(535, 350));
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        add(detailsPanel, BorderLayout.CENTER);

        JLabel noDetailsLabel = new JLabel("Select a pet to view details.");
        noDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.add(noDetailsLabel);

        //detailsPanel.setVisible(true);
        revalidate();
        repaint();
    }

    public void showPetDetails(Pet pet) {
        detailsPanel.removeAll(); // Clear previous details

        // Stop the existing timer (if any)
        if (healthUpdateTimer != null && healthUpdateTimer.isRunning()) {
            healthUpdateTimer.stop();
        }

        String fullSex = "";
        String fullLife = "";

        if (pet.getSex() == 'M')
            fullSex = "Male";
        else
            fullSex = "Female";

        if (pet.isAlive)
            fullLife = "Alive";
        else
            fullLife = "Deceased";

        if (pet == null) {
            JLabel noDetailsLabel = new JLabel("Select a pet to view details.");
            noDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            detailsPanel.add(noDetailsLabel);
        } else {
            // Add pet attributes
            addDetailLabel("Name: " + pet.getName());
            addDetailLabel("Type: " + pet.getType());
            addDetailLabel("Color: " + getColorName(pet.getColor()));
            addDetailLabel("Sex: " + fullSex);
            addDetailLabel("Age: " + pet.getCurrentAge());
            addDetailLabel("Life Expectancy: " + pet.getLifeExpectancy());
            addDetailLabel("Health: " + pet.getHealth());
            addDetailLabel("Status: " + fullLife);
        }

        healthUpdateTimer = new Timer(100, e -> updatePet(pet, detailsPanel));
        healthUpdateTimer.start();

        revalidate();
        repaint();
    }

    private void updatePet(Pet pet, JPanel detailsPanel) {
        SwingUtilities.invokeLater(() -> {
            detailsPanel.removeAll();
            String fullSex = "";
            String fullLife = "";
            String cuteName;

            if (pet.getSex() == 'M')
                fullSex = "Male";
            else
                fullSex = "Female";

            if (pet.isAlive)
                fullLife = "Alive";
            else
                fullLife = "Deceased";

            switch (pet.getType()) {
                case "Dog":
                    cuteName = "dog";
                    break;
                case "Cat":
                    cuteName = "kitty";
                    break;
                case "Bird":
                    cuteName = "birdy";
                    break;
                case "Fish":
                    cuteName = "fishy";
                    break;
                default:
                    cuteName = "thingy";
                    break;
            }

            addDetailLabel("Name: " + pet.getName());
            addDetailLabel("Type: " + pet.getType());
            addDetailLabel("Color: " + getColorName(pet.getColor()));
            addDetailLabel("Sex: " + fullSex);
            addDetailLabel("Age: " + pet.getCurrentAge());
            addDetailLabel("Life Expectancy: " + pet.getLifeExpectancy());
            addDetailLabel("Health: " + pet.getHealth());
            addDetailLabel("Status: " + fullLife);

            if (pet.getHealth() == 0) {
                addDetailLabel("");
                addDetailLabel("Rest in peace, " + pet.getName() + ". You will be missed...");
                addDetailLabel("Good " + cuteName + ". <3");
            }

            detailsPanel.revalidate();
            detailsPanel.repaint();
        });
    }


    private void addDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT); // Align text to the left
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsPanel.add(label);
        detailsPanel.add(Box.createVerticalStrut(5)); // Add some space between labels
    }

    private String getColorName(Color color) {
        Color Brown = new Color(100, 60, 25);
        Color Beige = new Color(207, 185, 151);
        String colorName = "";

        if (color == Color.RED)
            colorName = "Red";
        else if (color == Color.ORANGE)
            colorName = "Orange";
        else if (color == Color.YELLOW)
            colorName = "Yellow";
        else if (color == Color.GREEN)
            colorName = "Green";
        else if (color == Color.CYAN)
            colorName = "Cyan";
        else if (color == Color.BLUE)
            colorName = "Blue";
        else if (color == Color.MAGENTA)
            colorName = "Magenta";
        else if (color == Color.PINK)
            colorName = "Pink";
        else if (color == Color.LIGHT_GRAY)
            colorName = "Light Gray";
        else if (color == Color.GRAY)
            colorName = "Gray";
        else if (color == Color.DARK_GRAY)
            colorName = "Dark Gray";
        else if (color == Color.BLACK)
            colorName = "Black";
        else if (Objects.equals(color, Brown))
            colorName = "Brown";
        else if (Objects.equals(color, Beige))
            colorName = "Beige";

        return colorName;
    }
}