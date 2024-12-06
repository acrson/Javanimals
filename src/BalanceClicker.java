import javax.swing.*;
import java.awt.*;

public class BalanceClicker extends JButton {
    public BalanceClicker(String text) {
        super("");
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setText(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //draws circle
        if (getModel().isPressed()) {
            g2d.setColor(new Color(30, 130, 76)); //dark green when pressed
        } else {
            g2d.setColor(new Color(46, 204, 113)); //light green default
        }
        g2d.fillOval(0, 0, getWidth(), getHeight());

        //draw the text ($) in the center
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        String displayText = getText();
        int textWidth = fm.stringWidth(displayText);
        int textHeight = fm.getHeight();
        g2d.drawString(displayText, (getWidth() - textWidth) / 2, (getHeight() + textHeight / 3) / 2);

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(75, 75);
    }
}
