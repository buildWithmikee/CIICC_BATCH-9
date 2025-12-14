package BankSystem.src;

public class Account {
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
        return username.equals(user) && password.equals(pass);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("X Invalid amount.");
            return;
        }
        balance += amount;
        System.out.println("/ Deposit successful.");
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("X Insufficient balance.");
            return;
        }
        balance -= amount;
        System.out.println("/ Withdrawal successful.");
    }

    public String getFullName() {
        return fullName;
    }
}
