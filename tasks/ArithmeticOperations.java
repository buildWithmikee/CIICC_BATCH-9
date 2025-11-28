import java.util.Scanner;

public class Task7 {

    // Method 1: Addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Method 2: Subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Method 3: Multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Method 4: Division
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input two numbers
        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        // Call methods and print results
        System.out.println("\n---- RESULTS ----");
        System.out.println("Addition: " + add(num1, num2));
        System.out.println("Subtraction: " + subtract(num1, num2));
        System.out.println("Multiplication: " + multiply(num1, num2));
        System.out.println("Division: " + divide(num1, num2));
    }
}

