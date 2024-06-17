import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private BankAccount account;
    private JTextField amountField;

    public ATM(BankAccount account) {
        this.account = account;
        createUI();
    }

    private void createUI() {
        setTitle("ATM Machine");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("ATM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));

        amountField = new JTextField(10);

        JButton checkBalanceButton = createStyledButton("Check Balance");
        JButton depositButton = createStyledButton("Deposit");
        JButton withdrawButton = createStyledButton("Withdraw");
        JButton exitButton = createStyledButton("Exit");

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setLayout(new GridLayout(6, 1));
        add(titleLabel);
        add(amountField);
        add(checkBalanceButton);
        add(depositButton);
        add(withdrawButton);
        add(exitButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        button.setFont(new Font("Arial", Font.BOLD, 25)); 
        return button;
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(this, "Current Balance: $" + account.getBalance());
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposit successful.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds or invalid amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankAccount account = new BankAccount(1000.00);
                ATM atm = new ATM(account);
                atm.setVisible(true);
            }
        });
    }
}
