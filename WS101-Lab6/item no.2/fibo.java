import java.util.Scanner;

public class fibo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter N (number of terms): ");
        int n = scanner.nextInt();

        int a = 0, b = 1;

        System.out.println("Fibonacci Series up to " + n + " terms:");

        for (int i = 1; i <= n; i++) {
            System.out.print(a + " ");

            int next = a + b;
            a = b;
            b = next;
        }

        scanner.close();
    }
}