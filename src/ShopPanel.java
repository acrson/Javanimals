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
    private double userBalance;
    private final JLabel balanceLabel;

    public ShopPanel() {

        this.availableItems = ItemFactory.createItems();
        //can change this, setting the balance here so there is something
        this.userBalance = 30;

        this.balanceLabel = new JLabel("Balance: " + userBalance, JLabel.CENTER);

        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Shop Panel", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel itemPanel = getItemPanel();
        //added this incase we want to add more items
        JScrollPane itemScrollPane = new JScrollPane(itemPanel);
        this.add(itemScrollPane, BorderLayout.CENTER);

        //panel for balance button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        balanceLabel.setForeground(Color.BLACK);
        bottomPanel.add(balanceLabel, BorderLayout.NORTH);

        //add balance button to bottomPanel
        BalanceClicker addBalanceButton = new BalanceClicker("$");
        addBalanceButton.addActionListener(e -> addBalance(0.25));
        //creates new buttonpanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addBalanceButton);
        //add to bottom panel
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        //GUI display
        Color brown = new Color(120, 80, 50);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(brown);

        //text area
        System.out.println("Your current balance is: " + userBalance);
    }

    private JPanel getItemPanel() {
        JPanel itemPanel = new JPanel();
        //prints out the items in a grid layout with 1 column
        itemPanel.setLayout(new GridLayout(availableItems.size(), 1));

        //for loop that prints out all available items in the shop and prints out
        for (int i = 0; i < availableItems.size(); i++) {
            Item item = availableItems.get(i);

              Button itemButton = new Button(item.getName() + "- Price: " + item.getPrice());

            int index = i;
            itemButton.addActionListener(_ -> purchaseItem(index));
            itemPanel.add(itemButton);
        }
        return itemPanel;
    }

    //gets the users balance
    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("Balance: %.2f" , userBalance)); //%.2f formats with 2 decimals
        balanceLabel.revalidate();
        balanceLabel.repaint();
    }

    //used for a popup to ask user what animal you want the item to be applied to
    private String selectPetForItem(Item item) {
        String petType = JOptionPane.showInputDialog(this, "What is the pet would you apply this item to?");
        return switch (petType.toLowerCase()) {
            case "dog", "cat", "bird", "fish" -> petType;
            default -> {
                System.out.println("Invalid pet type");
                yield "";
            }
        };
    }
    
    public void purchaseItem(int itemIndex) {
        if (itemIndex < 0 || itemIndex >= availableItems.size()) {
            System.out.println("Invalid item index");
            return;
        }

        Item item = availableItems.get(itemIndex);

        //Ask user which pet they want to apply the item to
        String petType = selectPetForItem(item);

        //Check if the item is applicable to the selected pet
        if (!item.isApplicable(petType)) {
            // If not show an error message and prevent purchase
            JOptionPane.showMessageDialog(this,
                    "The item " + item.getName() + " cannot be used on a " + petType + ".",
                    "Item Not Applicable",
                    JOptionPane.ERROR_MESSAGE);

            //Explicitly indicate that no money has been deducted
            System.out.println("No money was deducted because the item cannot be used on this pet.");
            return; //Stop further execution of the method
        }

        //Check if user has enough balance to purchase the item
        if (userBalance >= item.getPrice()) {
            userBalance -= item.getPrice();
            System.out.println("You purchased " + item.getName() + ".");

            //Proceed with applying the item to the pet
            System.out.println(item.getName() + " applied to " + petType);

            //Update balance label after the purchase
            updateBalanceLabel();
        } else {
            //If the user doesn't have enough money
            JOptionPane.showMessageDialog(this,
                    "You don't have enough money to purchase " + item.getName() + ".",
                    "Insufficient Funds",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("You don't have enough money to purchase " + item.getName() + ".");
        }
    }

    private void addBalance(double amount) {
        userBalance += amount;
        System.out.println("Added " + amount + " to your balance.");
        updateBalanceLabel();
        System.out.println(userBalance);
    }
}