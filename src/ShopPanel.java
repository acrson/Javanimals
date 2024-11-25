import javax.swing.*;
import java.awt.*;
import java.util.List;
/*
is a pop-up panel like details panel that once clicked on shows
multiple items from the Item class that can be purchased with
money/coins/experience that is given when user gives animal attention
different items are different costs*/

public class ShopPanel extends JPanel {
    //adding these variables that keep track of available items and users balance.
    private final List<Item> availableItems;
    private int userBalance;
    private final Label balanceLabel;

    public ShopPanel() {
        this.availableItems = ItemFactory.createItems();
        this.userBalance = 0;

        this.balanceLabel = new Label("Balance: " + userBalance);
        this.setLayout(new BorderLayout());
        this.add(balanceLabel, BorderLayout.NORTH);

        Panel itemPanel = new Panel();
        itemPanel.setLayout(new GridLayout(availableItems.size(), 1));

        //for loop that prints out all available items in the shop and prints out
        for (int i = 0; i < availableItems.size(); i++) {
            Item item = availableItems.get(i);
            Button itemButton = new Button(item.getName() + "- Price: " + item.getPrice());

            int index = i;
            itemButton.addActionListener(_ -> purchaseItem(index));
            itemPanel.add(itemButton);
        }
        this.add(itemPanel, BorderLayout.CENTER);
        //prints out users balance
        System.out.println("Your current balance is: " + userBalance);

        //GUI display
        Color brown = new Color(120, 80, 50);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(brown);

        JLabel titleLabel = new JLabel("ShopPanel");
        add(titleLabel);
    }
    //gets the users balance
    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: " + userBalance);
    }
    //
    public boolean purchaseItem(int itemIndex) {
        if (itemIndex < 0 || itemIndex >= availableItems.size()) {
            System.out.println("Invalid item index");
            return false;
        }
        Item item = availableItems.get(itemIndex);
        if (userBalance >= item.getPrice()) {
            userBalance -= item.getPrice();
            System.out.println("You purchased " + item.getName() + ".");
            return true;
        } else {
            System.out.println("You don't have enough money to purchase " + item.getName() + ".");
            return false;
        }
    }
}