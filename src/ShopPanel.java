import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    public ShopPanel() {
        Color brown = new Color(120, 80, 50);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(brown);

        JLabel titleLabel = new JLabel("ShopPanel");
        add(titleLabel);
    }
}
