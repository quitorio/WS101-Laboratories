import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class productlist {

	static class Product {
		private final String name;
		private final double price;

		public Product(String name, double price) {
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public double getPrice() {
			return price;
		}

		@Override
		public String toString() {
			return name + " (" + price + ")";
		}
	}

	public static void main(String[] args) {
		List<Product> products = new ArrayList<>();
		// sample products
		products.add(new Product("Notebook", 25.50));
		products.add(new Product("Headphones", 75.00));
		products.add(new Product("Coffee Mug", 12.30));
		products.add(new Product("Smartphone", 399.99));
		products.add(new Product("USB Cable", 8.99));
		products.add(new Product("Backpack", 65.00));
		products.add(new Product("Monitor", 150.00));
		products.add(new Product("Keyboard", 45.00));

		System.out.println("Enter price threshold (e.g., 50.0):");
		double threshold;

		try (Scanner scanner = new Scanner(System.in)) {
			while (!scanner.hasNextDouble()) {
				System.out.print("Please enter a valid numeric threshold: ");
				scanner.next();
			}
			threshold = scanner.nextDouble();
		}

		// Use stream(), filter() with a lambda, and count()
		long count = products.stream()
				.filter(p -> p.getPrice() > threshold)
				.count();

		System.out.println("Number of products with price > " + threshold + ": " + count);

		// Optionally list them
		System.out.println("Matching products:");
		products.stream()
				.filter(p -> p.getPrice() > threshold)
				.forEach(p -> System.out.println("- " + p));
	}
}
