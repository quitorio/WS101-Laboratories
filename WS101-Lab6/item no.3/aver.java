public class aver {
	public static void main(String[] args) {
		System.out.println("Enter 5 numbers (separated by space or newline):");

		double[] nums = new double[5];
		double sum = 0.0;

		try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
			for (int i = 0; i < nums.length; i++) {
				while (!scanner.hasNextDouble()) {
					System.out.print("Please enter a valid number: ");
					scanner.next();
				}
				nums[i] = scanner.nextDouble();
				sum += nums[i];
			}
		}

		double average = sum / nums.length;

		System.out.print("Numbers: ");
		for (double v : nums) {
			System.out.print(v + " ");
		}
		System.out.println();

		System.out.println("Sum: " + sum);
		System.out.println("Average: " + average);
	}
}
