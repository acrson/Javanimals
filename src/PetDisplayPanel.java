import javax.swing.*;
import java.awt.*;

public class PetDisplayPanel extends JPanel {

    public PetDisplayPanel() {
        setLayout(new FlowLayout());

        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("PetDisplayPanel");
        add(titleLabel);
    }

    // Method to add a new panel for each pet
    public void addPetPanel(JPanel petPanel) {
        add(petPanel);
        revalidate();
        repaint();
    }
}
