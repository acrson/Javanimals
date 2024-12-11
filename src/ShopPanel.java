import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.List;

/*
is a pop-up panel like details panel that once clicked on shows
multiple items from the Item class that can be purchased with
money/coins/experience that is given when user gives animal attention
different items are different costs*/

public class ShopPanel extends JPanel {
    //Stores available shop items having the pets names as the keys
    private final List<Item> items;
    private double userBalance;
    private final JLabel balanceLabel;
    //Stores all pets in the database
    private final Map<String, Pet> petDatabase;

    //Initializes shopPanel with items, balance, and pet database
    public ShopPanel(Map<String, Item> items, double userBalance, Map<String, Pet> petDatabase) {
        //Gets the items from factory
        this.items = ItemFactory.createItems();
        this.userBalance = userBalance;
        this.petDatabase = petDatabase;
        this.balanceLabel = new JLabel("Balance: " + userBalance, JLabel.CENTER);

        this.setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        //Title
        JLabel titleLabel = new JLabel("Shop Panel", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);

        //Item Display Section
        JPanel itemPanel = getItemPanel();
        JScrollPane itemScrollPane = new JScrollPane(itemPanel);
        this.add(itemScrollPane, BorderLayout.CENTER);

        //Bottom Section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        balanceLabel.setForeground(Color.BLACK);
        bottomPanel.add(balanceLabel, BorderLayout.NORTH);

        //add balance button to bottomPanel
        BalanceClicker addBalanceButton = new BalanceClicker("$");
        addBalanceButton.addActionListener(e -> addBalance());
        //creates new buttonpanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addBalanceButton);
        //add to bottom panel
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(bottomPanel, BorderLayout.SOUTH);

        //GUI display
        Color brown = new Color(120, 80, 50);
        this.setBackground(brown);
        this.setPreferredSize(new Dimension(400, 400));

        System.out.println("Your current balance is: " + userBalance);
    }

    private JPanel getItemPanel() {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(items.size(), 1));

        //Get items from ItemFactory
        List<Item> itemList = ItemFactory.createItems();

        //Loop through the item list(much easier than map and displays in order)
        for (Item item : itemList) {
            //Gets item name
            String itemName = item.getName();
            Button itemButton = new Button(itemName + " - Price: $" + item.getPrice() + " - Health Boost: " + item.getPoints());

            //Handles button
            itemButton.addActionListener(e -> purchaseItem(itemName));
            itemButton.setPreferredSize(new Dimension(300, itemButton.getPreferredSize().height));
            itemPanel.add(itemButton);
        }

        return itemPanel;
    }

    //Updates and reflects current balance
    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("Balance: %.2f", userBalance)); //%.2f formats with 2 decimals
        balanceLabel.revalidate();
        balanceLabel.repaint();
    }

    //Used for a popup to ask user what animal you want the item to be applied to
    private String selectPetForItem(Item item) {
        String petName = JOptionPane.showInputDialog(this, "Enter the name of the pet you want to apply this to: ");
        if (!petDatabase.containsKey(petName)) {
            JOptionPane.showMessageDialog(this, "Pet not found!");//Error if animal does not exist
            return null;
        }
        Pet pet = petDatabase.get(petName);
        //Check if the item is applicable to the selected pet
        if (!item.getApplicableAnimals().contains(pet.getClass().getSimpleName())) {
            JOptionPane.showMessageDialog(this,
                    "The item " + item.getName() + " cannot be used on a " + pet.getClass().getSimpleName() + ".",
                    "Item Not Applicable",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return petName;
    }
    //Handles purchasing item and applying to pet
    public void purchaseItem(String itemName) {
        Item item = items.stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);//Returns null if no item found

        //Asks user which pet they want to apply the item to
        String petName = selectPetForItem(item);
        if (petName == null) {
            return;//Item can't be applied to the selected pet
        }

        //Retrieve selected pet from the database
        Pet pet = petDatabase.get(petName);

        //Checks if pets heal is 0, if so outputs an error saying Pet is dead
        if (pet.getHealth() == 0) {
            JOptionPane.showMessageDialog(this,
                petName + " is no longer alive. Items cannot be applied.",
                    "Pet Unavailable",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(petName + " is no longer alive. Items cannot be applied.");
            return;
        }

        //Check if user has enough balance to purchase an item
        if (userBalance >= item.getPrice()) {
            userBalance -= item.getPrice();//minuses item price from current balance
            System.out.println("You purchased " + item.getName() + " for " + petName + ".");

            pet.increaseHealth(item.getPoints());//Increases effects from item to the selected pet

            updateBalanceLabel();
        } else {
            //Displays if user doesn't have enough funds
            JOptionPane.showMessageDialog(this,
                    "You don't have enough money to purchase " + item.getName() + ".",
                    "Insufficient Funds",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("You don't have enough money to purchase " + item.getName() + ".");
        }
    }
    //Adds amount to users balance
    private void addBalance() {
        userBalance += 0.25;
        System.out.println("Added " + 0.25 + " to your balance.");
        updateBalanceLabel();
        System.out.println(userBalance);
        }
    }
