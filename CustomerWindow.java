import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerWindow extends JFrame {
    private List<MenuItem> menuItems;
    private List<MenuItem> selectedItems;

    private JLabel dateLabel;
    private JLabel subtotalLabel;
    private JLabel taxLabel;
    private JLabel tipLabel;
    private JLabel grandTotalLabel;

    private JList<String> menuList;
    private DefaultListModel<String> menuListModel;
    private JList<String> orderList;
    private DefaultListModel<String> orderListModel;

    public CustomerWindow() {
        menuItems = new ArrayList<>();
        selectedItems = new ArrayList<>();

        setTitle("Restaurant Ordering System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(5, 2));

        JLabel dateLabelTitle = new JLabel("Date:");
        dateLabel = new JLabel();
        JLabel subtotalLabelTitle = new JLabel("Subtotal:");
        subtotalLabel = new JLabel("$0.00");
        JLabel taxLabelTitle = new JLabel("Tax:");
        taxLabel = new JLabel("$0.00");
        JLabel tipLabelTitle = new JLabel("Tip:");
        tipLabel = new JLabel("$0.00");
        JLabel grandTotalLabelTitle = new JLabel("Grand Total:");
        grandTotalLabel = new JLabel("$0.00");

        infoPanel.add(dateLabelTitle);
        infoPanel.add(dateLabel);
        infoPanel.add(subtotalLabelTitle);
        infoPanel.add(subtotalLabel);
        infoPanel.add(taxLabelTitle);
        infoPanel.add(taxLabel);
        infoPanel.add(tipLabelTitle);
        infoPanel.add(tipLabel);
        infoPanel.add(grandTotalLabelTitle);
        infoPanel.add(grandTotalLabel);

        add(infoPanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 16));
        menuPanel.add(menuLabel);

        menuListModel = new DefaultListModel<>();
        menuList = new JList<>(menuListModel);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane menuScrollPane = new JScrollPane(menuList);
        menuPanel.add(menuScrollPane);

        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = menuList.getSelectedIndex();
                if (selectedIndex != -1) {
                    MenuItem selectedItem = menuItems.get(selectedIndex);
                    selectedItems.add(selectedItem);
                    orderListModel.addElement(selectedItem.getName() + " - " + selectedItem.getPrice() + "$");
                    updateTotal();
                }
            }
        });
        menuPanel.add(addButton);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        JLabel orderLabel = new JLabel("Order");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 16));
        orderPanel.add(orderLabel);

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION
        		);
        JScrollPane orderScrollPane = new JScrollPane(orderList);
        orderPanel.add(orderScrollPane);
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = orderList.getSelectedIndex();
                if (selectedIndex != -1) {
                    selectedItems.remove(selectedIndex);
                    orderListModel.remove(selectedIndex);
                    updateTotal();
                }
            }
        });
        orderPanel.add(removeButton);

        JButton submitButton = new JButton("Submit Order");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform further actions like recording the order, generating receipt, etc.
                dispose();
            }
        });

        add(menuPanel, BorderLayout.WEST);
        add(orderPanel, BorderLayout.EAST);
        add(submitButton, BorderLayout.SOUTH);

        // Set the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        dateLabel.setText(formatter.format(now));
    }

    public void setMenuItems(List<MenuItem> items) {
        menuItems = items;
        for (MenuItem item : items) {
            menuListModel.addElement(item.getName() + " - " + item.getPrice() + "$");
        }
    }

    private void updateTotal() {
        double subtotal = 0.0;
        for (MenuItem item : selectedItems) {
            subtotal += item.getPrice();
        }
        double tax = subtotal * 0.1; // Assuming 10% tax
        double tip = subtotal * 0.15; // Assuming 15% tip
        double grandTotal = subtotal + tax + tip;

        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        tipLabel.setText(String.format("$%.2f", tip));
        grandTotalLabel.setText(String.format("$%.2f", grandTotal));
    }
}
