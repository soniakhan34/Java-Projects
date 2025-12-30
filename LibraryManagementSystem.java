import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfessionalLibrarySystem extends JFrame {

    JTextField txtId, txtName, txtAuthor;
    DefaultTableModel model;

    public ProfessionalLibrarySystem() {
        setTitle("📚 Library Management System");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245)); // light grey background
        add(panel);

        // Title Label
        JLabel lblTitle = new JLabel("📖 Library Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitle.setForeground(new Color(30, 60, 120)); // professional dark blue
        lblTitle.setBounds(150, 10, 400, 40);
        panel.add(lblTitle);

        // Labels
        JLabel lblId = new JLabel("Book ID:");
        lblId.setFont(new Font("Arial", Font.BOLD, 14));
        lblId.setBounds(20, 70, 100, 25);
        panel.add(lblId);

        JLabel lblName = new JLabel("Book Name:");
        lblName.setFont(new Font("Arial", Font.BOLD, 14));
        lblName.setBounds(20, 110, 100, 25);
        panel.add(lblName);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setFont(new Font("Arial", Font.BOLD, 14));
        lblAuthor.setBounds(20, 150, 100, 25);
        panel.add(lblAuthor);

        // Text Fields
        txtId = new JTextField();
        txtId.setBounds(120, 70, 180, 25);
        panel.add(txtId);

        txtName = new JTextField();
        txtName.setBounds(120, 110, 180, 25);
        panel.add(txtName);

        txtAuthor = new JTextField();
        txtAuthor.setBounds(120, 150, 180, 25);
        panel.add(txtAuthor);

        // Buttons with professional colors
        JButton btnAdd = new JButton("➕ Add Book");
        btnAdd.setBounds(20, 200, 140, 35);
        btnAdd.setBackground(new Color(34, 139, 34)); // green
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        panel.add(btnAdd);

        JButton btnIssue = new JButton("📤 Issue Book");
        btnIssue.setBounds(170, 200, 140, 35);
        btnIssue.setBackground(new Color(70, 130, 180)); // blue
        btnIssue.setForeground(Color.WHITE);
        btnIssue.setFont(new Font("Arial", Font.BOLD, 14));
        btnIssue.setFocusPainted(false);
        panel.add(btnIssue);

        JButton btnReturn = new JButton("📥 Return Book");
        btnReturn.setBounds(320, 200, 140, 35);
        btnReturn.setBackground(new Color(255, 140, 0)); // orange
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturn.setFocusPainted(false);
        panel.add(btnReturn);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Book ID");
        model.addColumn("Book Name");
        model.addColumn("Author");
        model.addColumn("Status");

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(100, 149, 237));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 260, 690, 180);
        panel.add(sp);

        // Button Actions
        btnAdd.addActionListener(e -> {
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtAuthor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Please fill all fields");
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
                JOptionPane.showMessageDialog(this, "⚠️ Select a book to issue");
            }
        });

        btnReturn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.setValueAt("Available", row, 3);
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Select a book to return");
            }
        });
    }

    void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAuthor.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfessionalLibrarySystem().setVisible(true));
    }
}
