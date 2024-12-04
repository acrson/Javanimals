import javax.swing.*;
import java.awt.*;

public class PetDisplayPanel extends JPanel {
    JPanel petHome;

    public PetDisplayPanel() {
        setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Pet Display Panel");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel to contain the pet panels and use FlowLayout to allow for dynamic addition
        petHome = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));  // FlowLayout with padding
        add(petHome, BorderLayout.CENTER);  // Add to center of BorderLayout
    }

    // Method to add a new panel for each pet
    public void addPetPanel(JPanel petPanel) {
        petHome.add(petPanel);
        revalidate();
        repaint();
    }
}
