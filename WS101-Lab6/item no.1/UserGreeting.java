import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UserGreeting {
    public static void main(String[] args) throws IOException {

        // Using Scanner for reading age
        Scanner scanner = new Scanner(System.in);

        // Using newer IO class (BufferedReader) for reading name
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter your name: ");
        String name = reader.readLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.println("\nHello " + name + "! You are " + age + " years young.");
        
        scanner.close();
    }
}