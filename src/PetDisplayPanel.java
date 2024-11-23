import javax.swing.*;
import java.awt.*;

public class PetDisplayPanel extends JPanel {
    public PetDisplayPanel() {
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("PetDisplayPanel");
        add(titleLabel);
    }
}
