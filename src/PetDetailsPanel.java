import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PetDetailsPanel extends JPanel {
    private JButton toggleButton;
    private JPanel detailsPanel;
    private boolean isExpanded = false;

    public PetDetailsPanel(Pet pet) {
        Color skyBlue = new Color(90, 150, 255);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(skyBlue);

        toggleButton = new JButton("PetDetailsPanel");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isExpanded = !isExpanded;
                detailsPanel.setVisible(isExpanded);
                toggleButton.setText(isExpanded ? "Hide Details" : "Show Details");
            }
        });
        add(toggleButton);

        detailsPanel = new JPanel();
        detailsPanel.setBackground(skyBlue);
        detailsPanel.add(new JLabel("Name: " + pet.getName()));
        detailsPanel.add(new JLabel("Sex: " + pet.getSex()));
        detailsPanel.add(new JLabel("Age: " + pet.getCurrentAge()));
        detailsPanel.add(new JLabel("Color: " + pet.getColor()));
        detailsPanel.add(new JLabel("Life Expectancy: " + pet.getLifeExpectancy()));
        detailsPanel.add(new JLabel("Breed: " + pet.getType()));
        detailsPanel.setVisible(false);
    }
}