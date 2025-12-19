import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BankSystem {

    static Scanner sc = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();
    static Map<String, Admin> admins = new HashMap<>();

    public static void main(String[] args) {

        // Load users from CSV at startup
        loadUsersFromCSV("users.csv");

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
                    System.out.print("Choose a username: ");
                    String newUsername = sc.nextLine();

                    if(accounts.containsKey(newUsername)){
                        System.out.println("❌ Username already exists.");
                        break;
                    }

                    // Prompt for password
                    System.out.print("Enter password: ");
                    String newPassword = sc.nextLine();

                    // Prompt for full name
                    System.out.print("Enter full name: ");
                    String fullName = sc.nextLine();

    // Create new account
    Account newAcc = new Account(newUsername, newPassword, fullName, 0);
    accounts.put(newUsername, newAcc);

    // Save to CSV so it persists
    saveAccountToCSV(newAcc, "users.csv");

    System.out.println("✅ Account created successfully! You can now login.");
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

    // ================= Save New Account =================
    static void saveAccountToCSV(Account acc, String fileName) {
        try (FileWriter fw = new FileWriter(fileName, true);
             PrintWriter pw = new PrintWriter(fw)) {
            // Format: username,password,fullName,balance
            pw.println(acc.getUsername() + "," + acc.getPassword() + "," + acc.getFullName() + "," + acc.getBalance());
            System.out.println("✅ Account saved to file.");
        } catch (IOException e) {
            System.out.println("❌ Error saving account: " + e.getMessage());
        }
    }

// ================= CSV Loader =================
static void loadUsersFromCSV(String fileName) {
    try (Scanner fileScanner = new Scanner(new File(fileName))) {
        int loaded = 0;

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");

            if (parts.length < 4) {
                System.out.println("❌ Skipping invalid line: " + line);
                continue;
            }

            String username = parts[0].replaceAll("[^\\w]", "").trim();
            String password = parts[1].trim();
            String fullName = parts[2].trim();

            // Clean balance for parsing (remove $ and commas)
            String balStr = parts[3].replaceAll("[$,\\s]", "").trim();

            if (balStr.isEmpty()) {
                System.out.println("❌ Empty balance for user: " + username + ", skipping...");
                continue;
            }

            try {
                double balance = Double.parseDouble(balStr);
                // Store the balance as double, display with $ later
                accounts.put(username, new Account(username, password, fullName, balance));
                loaded++;
            } catch (NumberFormatException e) {
                System.out.println("❌ Could not parse balance for user: " + username + ", skipping...");
            }
        }

        System.out.println("✅ Users loaded from CSV: " + loaded);
    } catch (FileNotFoundException e) {
        System.out.println("❌ CSV file not found: " + fileName);
    }
}


    // ================= ADMIN =================
    static void adminLogin() {
        System.out.print("\nAdmin Username: ");
        String user = sc.nextLine();
        System.out.print("Admin Password: ");
        String pass = sc.nextLine();

        Admin admin = admins.get(user);

        if (admin == null || !admin.login(user, pass)) {
            System.out.println("❌ Wrong admin credentials.");
            return;
        }

        while (true) {
            System.out.println("\n------ ADMIN MENU ------");
            System.out.println("1. Create User");
            System.out.println("2. View Users");
            System.out.println("3. Logout");
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
                    System.out.print("Username: ");
                    String u = sc.nextLine();
                    if (accounts.containsKey(u)) {
                        System.out.println("❌ User already exists.");
                        break;
                    }
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    System.out.print("Full Name: ");
                    String n = sc.nextLine();
                    accounts.put(u, new Account(u, p, n, 0));
                    System.out.println("✅ User created successfully.");
                    break;

                case 2:
                    System.out.println("\n--- USER LIST ---");
                    for (String key : accounts.keySet()) {
                        System.out.println("- " + key);
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    // ================= USER =================
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
            System.out.println("\n------ USER MENU ------");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
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
                    break;
                case 3:
                    System.out.print("Enter amount: ");
                    double withdrawAmt = getPositiveDouble();
                    acc.withdraw(withdrawAmt);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    // Helper method for safe double input
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

    public double getBalance() {
        return balance;
    }

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

    // ======= ADD THESE GETTERS =======
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
    // ================================
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
