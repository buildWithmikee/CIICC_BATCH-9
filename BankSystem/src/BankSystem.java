package BankSystem.src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankSystem {

    static Scanner sc = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();
    static Map<String, Admin> admins = new HashMap<>();

    public static void main(String[] args) {

        // default data
        accounts.put("user10", new Account("user10", "pass10", "Yixuan", 300.00));
        admins.put("admin", new Admin("admin", "admin123"));

        while (true) {
            System.out.println("\n==============================");
            System.out.println("      BANK SYSTEM MAIN MENU");
            System.out.println("==============================");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> userLogin();
                case 3 -> {
                    System.out.println("\nThank you for using the Bank System!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid input.");
            }
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
            System.out.println("2. View All Users");
            System.out.println("3. Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Username: ");
                String u = sc.nextLine();
                if (accounts.containsKey(u)) {
                    System.out.println("❌ User already exists.");
                    continue;
                }

                System.out.print("Password: ");
                String p = sc.nextLine();
                System.out.print("Full Name: ");
                String n = sc.nextLine();

                accounts.put(u, new Account(u, p, n, 0));
                System.out.println("✅ User created successfully.");

            } else if (choice == 2) {
                System.out.println("\n--- USER LIST ---");
                for (String key : accounts.keySet()) {
                    System.out.println("- " + key);
                }

            } else {
                break;
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
            System.out.println("❌ Invalid user login.");
            return;
        }

        while (true) {
            System.out.println("\n------ USER MENU ------");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> System.out.println("💰 Balance: " + acc.getBalance());
                case 2 -> {
                    System.out.print("Enter amount: ");
                    acc.deposit(sc.nextDouble());
                }
                case 3 -> {
                    System.out.print("Enter amount: ");
                    acc.withdraw(sc.nextDouble());
                }
                default -> {
                    return;
                }
            }
        }
    }
}

