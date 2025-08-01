import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankGUI extends JFrame {
    Account currentAccount;

    JTextField nameField = new JTextField(10);
    JTextField accNoField = new JTextField(10);
    JTextField balanceField = new JTextField(10);
    JTextField extraField = new JTextField(10); // interest or overdraft

    JButton createBtn = new JButton("Create Account");
    JButton depositBtn = new JButton("Deposit");
    JButton withdrawBtn = new JButton("Withdraw");
    JButton showBtn = new JButton("Show Details");

    JComboBox<String> accType = new JComboBox<>(new String[]{"Savings", "Current"});

    JTextArea outputArea = new JTextArea(8, 30);

    public BankGUI() {
        setTitle("Java Banking System");
        setSize(400, 450);
        setLayout(new FlowLayout());

        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Account No:")); add(accNoField);
        add(new JLabel("Initial Balance:")); add(balanceField);
        add(new JLabel("Interest % / Overdraft ₹:")); add(extraField);
        add(new JLabel("Account Type:")); add(accType);
        add(createBtn);

        add(depositBtn); add(withdrawBtn); add(showBtn);
        add(new JScrollPane(outputArea));

        createBtn.addActionListener(e -> createAccount());
        depositBtn.addActionListener(e -> transaction(true));
        withdrawBtn.addActionListener(e -> transaction(false));
        showBtn.addActionListener(e -> showDetails());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void createAccount() {
        try {
            String name = nameField.getText();
            int accNo = Integer.parseInt(accNoField.getText());
            double balance = Double.parseDouble(balanceField.getText());
            double extra = Double.parseDouble(extraField.getText());

            if (accType.getSelectedItem().equals("Savings")) {
                currentAccount = new SavingsAccount(name, accNo, balance, extra);
            } else {
                currentAccount = new CurrentAccount(name, accNo, balance, extra);
            }
            outputArea.setText("✅ Account Created!\n");
        } catch (Exception ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    void transaction(boolean isDeposit) {
        if (currentAccount == null) {
            outputArea.setText("❌ No account created!");
            return;
        }
        String input = JOptionPane.showInputDialog(this, (isDeposit ? "Deposit" : "Withdraw") + " Amount:");
        try {
            double amt = Double.parseDouble(input);
            if (isDeposit)
                currentAccount.deposit(amt);
            else
                currentAccount.withdraw(amt);
            outputArea.setText("✅ Transaction Successful!\n");
        } catch (Exception e) {
            outputArea.setText("❌ Transaction Failed: " + e.getMessage());
        }
    }

    void showDetails() {
        if (currentAccount == null) {
            outputArea.setText("❌ No account available!");
        } else {
            outputArea.setText(currentAccount.getDetails() + "\n\nTotal Accounts: " + Account.getTotalAccounts());
        }
    }
}