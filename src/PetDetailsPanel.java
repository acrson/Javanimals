import javax.swing.*;
import java.awt.*;

public class PetDetailsPanel extends JPanel {
    public PetDetailsPanel() {
        Color skyBlue = new Color(90, 150, 255);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(skyBlue);

        JLabel titleLabel = new JLabel("PetDetailsPanel");
        add(titleLabel);
    }
}
