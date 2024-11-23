import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    public InventoryPanel() {
        Color lightYellow = new Color(255, 255, 200);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(lightYellow);

        JLabel titleLabel = new JLabel("InventoryPanel");
        add(titleLabel);
    }
}
