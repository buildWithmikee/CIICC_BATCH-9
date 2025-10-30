public class tasksheet113 {
    public static void main(String[] args) {
        int check_number = 10; // initial value
        String message; // store result

        // Loop from 1 to check_number
        for (int i = 1; i <= check_number; i++) {
            // Check if the number is even or odd 
            message = (i % 2 == 0) ? i + " is even number" : i + " is odd number";
            System.out.println(message);
        }
    }
}
