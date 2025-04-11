import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Inventory_mang {
     // HashMap to store item names and their quantities
    private HashMap<String, Integer> stock = new HashMap<>();
    
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField itemNameField, quantityField;

    // Constructor: sets up the GUI
    public Inventory_mang() {
        frame = new JFrame("Inventory Management");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Input labels and fields
        JLabel itemLabel = new JLabel("Item Name:");
        itemNameField = new JTextField(15);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);

        // Buttons for operations
        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton updateButton = new JButton("Update Item");
        JButton displayButton = new JButton("Display Items");

        // Text area to display inventory
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        // Assign button actions using lambda expressions
        addButton.addActionListener(e -> addItem());
        removeButton.addActionListener(e -> removeItem());
        updateButton.addActionListener(e -> updateItem());
        displayButton.addActionListener(e -> displayItems());

        frame.add(itemLabel);
        frame.add(itemNameField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(updateButton);
        frame.add(displayButton);
        frame.add(new JScrollPane(displayArea));

        frame.setVisible(true);
    }

    // Adds a new item or increases quantity if item exists
    private void addItem() {
        String item = itemNameField.getText();
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            stock.put(item, stock.getOrDefault(item, 0) + quantity);
            displayArea.setText("Item added successfully!\n" + item + ": " + stock.get(item));
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid quantity!");
        }
    }

    // Removes an item from the inventory
    private void removeItem() {
        String item = itemNameField.getText();
        if (stock.containsKey(item)) {
            stock.remove(item);
            displayArea.setText("Item removed successfully!");
        } else {
            displayArea.setText("Item does not exist!");
        }
    }

     // Updates the quantity of an existing item
    private void updateItem() {
        String item = itemNameField.getText();
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if (stock.containsKey(item)) {
                stock.put(item, quantity);
                displayArea.setText("Item updated successfully!\n" + item + ": " + quantity);
            } else {
                displayArea.setText("Item does not exist!");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid quantity!");
        }
    }

    // Displays all items and their quantities
    private void displayItems() {
        StringBuilder sb = new StringBuilder("Current Inventory:\n");
        for (String key : stock.keySet()) {
            sb.append(key).append(": ").append(stock.get(key)).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        new Inventory_mang();
    }
}
