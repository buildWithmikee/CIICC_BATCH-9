import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import BankSystem.Account;
import BankSystem.repository.UserRepository;
import BankSystem.repository.CsvUserRepository;


public class BankSystem {

    static Scanner sc = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();
    static Map<String, Admin> admins = new HashMap<>();
    
    // CSV file path in project root
    static final String CSV_FILE = "users.csv";

    public static void main(String[] args) {

        // Load users from CSV at startup
        loadUsersFromCSV(CSV_FILE);

        // Default admin/user
        admins.put("admin", new Admin("admin", "admin123"));
        accounts.putIfAbsent("user10", new Account("user10", "pass10", "Yixuan", 300));

        while (true) {
            System.out.println("\n==================================");
            System.out.println("             COINTRIX");
            System.out.println("   Powering Your Digital Assets");
            System.out.println("==================================");
            System.out.println("1. Sign In Account");
            System.out.println("2. Register Account");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    userLogin();
                    break;
                case 2:
                    registerAccount();
                    break;
                case 3:
                    System.out.println("Thank you for using Cointrix!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid input.");
            }
        }
    }

// ================= ENSURE CSV EXISTS =================
static void ensureCSVExists() {
    File file = new File(CSV_FILE);
    if (!file.exists()) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println("first_name,last_name,email,balance");
            System.out.println("✅ Created new CSV with header: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error creating CSV: " + e.getMessage());
        }
    }
}

// ================= REGISTER ACCOUNT =================
static void registerAccount() {
    System.out.print("Choose a username/email: ");
    String newUsername = sc.nextLine().trim();

    if (accounts.containsKey(newUsername)) {
        System.out.println("❌ Username already exists.");
        return;
    }

    System.out.print("Enter password: ");
    String newPassword = sc.nextLine().trim();

    System.out.print("Enter full name: ");
    String fullName = sc.nextLine().trim();

    Account newAcc = new Account(newUsername, newPassword, fullName, 0);
    accounts.put(newUsername, newAcc);

    saveAccountToCSV(newAcc);

    System.out.println("✅ Account created successfully! You can now login.");
}

// ================= SAVE NEW ACCOUNT =================
static void saveAccountToCSV(Account acc) {
    File file = new File(CSV_FILE);
    System.out.println("Saving account to: " + file.getAbsolutePath());

    try (FileWriter fw = new FileWriter(file, true);
         PrintWriter pw = new PrintWriter(fw)) {

        // Split full name into first & last
        String[] nameParts = acc.getFullName().trim().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        // Format balance as "$37,063.78"
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedBalance = nf.format(acc.getBalance());

        // CSV format: first_name,last_name,email,balance
        pw.println(firstName + "," +
                   lastName + "," +
                   acc.getUsername() + "," +
                   "\"" + formattedBalance + "\"");

        System.out.println("✅ Account appended to CSV.");

    } catch (IOException e) {
        System.out.println("❌ Error saving account: " + e.getMessage());
    }
}

// ================= CSV LOADER =================
static void loadUsersFromCSV(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
        System.out.println("No existing CSV found at: " + file.getAbsolutePath());
        return;
    }

    try (Scanner fileScanner = new Scanner(file)) {
        int loaded = 0;

        // Skip header
        if (fileScanner.hasNextLine()) fileScanner.nextLine();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length < 4) continue;

            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            String email = parts[2].trim();

            // Remove "$", ",", quotes
            String balStr = parts[3].replace("\"", "")
                                     .replace("$", "")
                                     .replace(",", "")
                                     .trim();

            double balance = Double.parseDouble(balStr);
            String fullName = firstName + " " + lastName;

            accounts.put(email, new Account(email, "N/A", fullName, balance));
            loaded++;
        }

        System.out.println("✅ Users loaded from currentusers.csv: " + loaded);

    } catch (Exception e) {
        System.out.println("❌ Error loading CSV: " + e.getMessage());
    }
}


    // ================= USER LOGIN =================
    static void userLogin() {
        System.out.print("\nUsername: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        Account acc = accounts.get(user);
        if (acc == null || !acc.login(user, pass)) {
            System.out.println("❌ Invalid login.");
            return;
        }

        while (true) {
            System.out.println("\n=========== USER MENU ===========");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");

            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("💰 Balance: " + acc.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    double depositAmt = getPositiveDouble();
                    acc.deposit(depositAmt);
                    saveAllAccountsToCSV(); // persist balance change
                    break;
                case 3:
                    System.out.print("Enter amount: ");
                    double withdrawAmt = getPositiveDouble();
                    acc.withdraw(withdrawAmt);
                    saveAllAccountsToCSV(); // persist balance change
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    // Save all accounts to CSV (overwrite)
static void saveNewAccountToCSV(Account acc) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE, true))) {

        // Split full name into first and last
        String[] nameParts = acc.getFullName().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        // Format balance like "$37,063.78"
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedBalance = nf.format(acc.getBalance());

        pw.println(
            firstName + "," +
            lastName + "," +
            acc.getUsername() + "," +
            "\"" + formattedBalance + "\""
        );

    } catch (IOException e) {
        System.out.println("❌ Error saving new account: " + e.getMessage());
    }
}

    // Helper for double input
    static double getPositiveDouble() {
        while (true) {
            String input = sc.nextLine();
            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.print("❌ Enter a positive amount: ");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid number. Try again: ");
            }
        }
    }
}

// ========== ACCOUNT CLASS ==========
class Account {
    private String username;
    private String password;
    private String fullName;
    private double balance;

    public Account(String username, String password, String fullName, double balance) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.balance = balance;
    }

    public boolean login(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    public double getBalance() { return balance; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }

    public void deposit(double amt) {
        balance += amt;
        System.out.println("✅ Deposited: " + amt + " | New balance: " + balance);
    }

    public void withdraw(double amt) {
        if (amt > balance) {
            System.out.println("❌ Insufficient balance.");
        } else {
            balance -= amt;
            System.out.println("✅ Withdrawn: " + amt + " | New balance: " + balance);
        }
    }
}

// ========== ADMIN CLASS ==========
class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }
}
