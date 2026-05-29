package Triangle.src;
public class TriangleClassifier {
    public static String classify(int a, int b, int c) {
        if (!isValidInput(a) || !isValidInput(b) || !isValidInput(c)) {
            return "Invalid Input";
        }

        if (!isTriangle(a, b, c)) {
            return "Not a Triangle";
        }

        if (a == b && b == c) {
            return "Equilateral";
        }

        if (a == b || b == c || a == c) {
            return "Isosceles";
        }

        return "Scalene";
    }

    private static boolean isValidInput(int value) {
        return value >= 1 && value <= 100;
    }

    private static boolean isTriangle(int a, int b, int c) {
        return a + b > c && a + c > b && b + c > a;
    }
}
