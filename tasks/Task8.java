public class Task8 {

    // Method using variable arguments
    public static int[] computeCumulative(int... numbers) {
        int[] results = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            int cumSum = 0;

            // Compute cumulative sum from 1 to n
            for (int j = 1; j <= n; j++) {
                cumSum += j;
            }

            results[i] = cumSum;
        }

        return results;
    }

    public static void main(String[] args) {
        int[] output = computeCumulative(4, 5, 10);

        System.out.println("Cumulative sums:");

        for (int value : output) {
            System.out.println(value);
        }
    }
}

