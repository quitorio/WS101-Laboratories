public class rec {
	// Rectangle class with length and width fields and calculateArea() method
	static class Rectangle {
		private double length;
		private double width;

		public Rectangle(double length, double width) {
			this.length = length;
			this.width = width;
		}

		public double calculateArea() {
			return length * width;
		}

		public double getLength() {
			return length;
		}

		public double getWidth() {
			return width;
		}

		public void setLength(double length) {
			this.length = length;
		}

		public void setWidth(double width) {
			this.width = width;
		}

		@Override
		public String toString() {
			return "Rectangle[length=" + length + ", width=" + width + "]";
		}
	}

	// Demonstration of creating and using Rectangle objects from user input
	public static void main(String[] args) {
		System.out.println("Rectangle Area Calculator");

		double length;
		double width;

		try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
			System.out.print("Enter length: ");
			while (!scanner.hasNextDouble()) {
				System.out.print("Please enter a valid number for length: ");
				scanner.next();
			}
			length = scanner.nextDouble();

			System.out.print("Enter width: ");
			while (!scanner.hasNextDouble()) {
				System.out.print("Please enter a valid number for width: ");
				scanner.next();
			}
			width = scanner.nextDouble();
		}

		Rectangle r = new Rectangle(length, width);
		System.out.println("Created: " + r);
		System.out.println("Area: " + r.calculateArea());
	}
}
