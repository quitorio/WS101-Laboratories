public class palin {
	public static void main(String[] args) {
		System.out.print("Enter a string: ");

		String input;
		try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
			if (scanner.hasNextLine()) {
				input = scanner.nextLine();
			} else {
				System.out.println("No input provided.");
				return;
			}
		}

		// Normalize input: remove non-alphanumeric characters and convert to lower case
		String normalized = input.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

		// Reverse the normalized string
		String reversed = new StringBuilder(normalized).reverse().toString();

		if (normalized.equals(reversed)) {
			System.out.println("\"" + input + "\" is a palindrome.");
		} else {
			System.out.println("\"" + input + "\" is not a palindrome.");
		}
	}
}
