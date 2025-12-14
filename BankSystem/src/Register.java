import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

