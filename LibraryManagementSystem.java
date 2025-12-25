// Simple Library Management System using Java Swing
// Features: Add Book, Issue Book, Return Book, View Books

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LibraryManagementSystem extends JFrame {

    JTextField txtId, txtName, txtAuthor;
    DefaultTableModel model;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255));
        add(panel);

        // Labels
        JLabel lblTitle = new JLabel("Library Management System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(200, 10, 350, 30);
        panel.add(lblTitle);

        JLabel lblId = new JLabel("Book ID:");
        lblId.setBounds(20, 60, 100, 25);
        panel.add(lblId);

        JLabel lblName = new JLabel("Book Name:");
        lblName.setBounds(20, 95, 100, 25);
        panel.add(lblName);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(20, 130, 100, 25);
        panel.add(lblAuthor);

        // Text Fields
        txtId = new JTextField();
        txtId.setBounds(120, 60, 150, 25);
        panel.add(txtId);

        txtName = new JTextField();
        txtName.setBounds(120, 95, 150, 25);
        panel.add(txtName);

        txtAuthor = new JTextField();
        txtAuthor.setBounds(120, 130, 150, 25);
        panel.add(txtAuthor);

        // Buttons
        JButton btnAdd = new JButton("Add Book");
        btnAdd.setBounds(20, 180, 120, 30);
        panel.add(btnAdd);

        JButton btnIssue = new JButton("Issue Book");
        btnIssue.setBounds(150, 180, 120, 30);
        panel.add(btnIssue);

        JButton btnReturn = new JButton("Return Book");
        btnReturn.setBounds(280, 180, 120, 30);
        panel.add(btnReturn);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Book ID");
        model.addColumn("Book Name");
        model.addColumn("Author");
        model.addColumn("Status");

        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 230, 650, 200);
        panel.add(sp);

        // Button Actions
        btnAdd.addActionListener(e -> {
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtAuthor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
            } else {
                model.addRow(new Object[]{
                        txtId.getText(),
                        txtName.getText(),
                        txtAuthor.getText(),
                        "Available"
                });
                clearFields();
            }
        });

        btnIssue.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.setValueAt("Issued", row, 3);
            } else {
                JOptionPane.showMessageDialog(this, "Select a book to issue");
            }
        });

        btnReturn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.setValueAt("Available", row, 3);
            } else {
                JOptionPane.showMessageDialog(this, "Select a book to return");
            }
        });
    }

    void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAuthor.setText("");
    }

    public static void main(String[] args) {
        new LibraryManagementSystem().setVisible(true);
    }
}
