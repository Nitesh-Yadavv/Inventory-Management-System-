import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Inventory_mang {
    private HashMap<String, Integer> stock = new HashMap<>();
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField itemNameField, quantityField;

    public Inventory_mang() {
        frame = new JFrame("Inventory Management");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel itemLabel = new JLabel("Item Name:");
        itemNameField = new JTextField(15);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);

        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton updateButton = new JButton("Update Item");
        JButton displayButton = new JButton("Display Items");

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

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

    private void removeItem() {
        String item = itemNameField.getText();
        if (stock.containsKey(item)) {
            stock.remove(item);
            displayArea.setText("Item removed successfully!");
        } else {
            displayArea.setText("Item does not exist!");
        }
    }

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
