import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String username;
    private String password;
    private double balance;
    private List<Transaction> transactionHistory;

    // Constructor
    public BankAccount(String username, String password, double initialDeposit) {
        this.username = username;
        this.password = password;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        recordTransaction("Account created with initial deposit of $" + initialDeposit);
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            recordTransaction("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recordTransaction("Withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Record transaction
    private void recordTransaction(String transactionDetail) {
        transactionHistory.add(new Transaction(transactionDetail));
    }

    // Get current balance
    public double getBalance() {
        return balance;
    }

    // View transaction history
    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // View account details
    public void viewAccountDetails() {
        System.out.println("Account Holder: " + username);
        System.out.println("Current Balance: $" + balance);
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String detail;
    private String timestamp;

    // Constructor
    public Transaction(String detail) {
        this.detail = detail;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + detail;
    }
}
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {

    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static BankAccount currentAccount = null;

    public static void main(String[] args) {
        System.out.println("Welcome to the Online Banking System");

        // Main loop
        while (true) {
            if (currentAccount == null) {
                loginMenu();
            } else {
                accountMenu();
            }
        }
    }

    // Show login menu
    private static void loginMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("Exiting the system.");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    // Login process
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(username)) {
            BankAccount account = accounts.get(username);
            if (account.getPassword().equals(password)) {
                currentAccount = account;
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid password.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Register a new user
    private static void register() {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        System.out.print("Enter initial deposit amount: $");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (accounts.containsKey(username)) {
            System.out.println("Username already exists.");
        } else {
            BankAccount newAccount = new BankAccount(username, password, initialDeposit);
            accounts.put(username, newAccount);
            currentAccount = newAccount;
            System.out.println("Account created successfully!");
        }
    }

    // Show account menu after login
    private static void accountMenu() {
        System.out.println("\nSelect an operation:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. View Transaction History");
        System.out.println("5. View Account Details");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                depositMoney();
                break;
            case 2:
                withdrawMoney();
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                viewTransactionHistory();
                break;
            case 5:
                viewAccountDetails();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Deposit money
    private static void depositMoney() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
    }

    // Withdraw money
    private static void withdrawMoney() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        currentAccount.withdraw(amount);
    }

    // Check balance
    private static void checkBalance() {
        System.out.println("Your balance: $" + currentAccount.getBalance());
    }

    // View transaction history
    private static void viewTransactionHistory() {
        currentAccount.viewTransactionHistory();
    }

    // View account details
    private static void viewAccountDetails() {
        currentAccount.viewAccountDetails();
    }

    // Logout
    private static void logout() {
        System.out.println("Logged out successfully.");
        currentAccount = null;
    }
}













