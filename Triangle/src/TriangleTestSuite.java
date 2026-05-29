package Triangle.src;
public class TriangleTestSuite {
    private static class TestCase {
        final int a;
        final int b;
        final int c;
        final String expected;

        TestCase(int a, int b, int c, String expected) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        TestCase[] cases = new TestCase[] {
            // Valid triangles: equilateral
            new TestCase(1, 1, 1, "Equilateral"),
            new TestCase(100, 100, 100, "Equilateral"),

            // Valid triangles: isosceles
            new TestCase(2, 2, 3, "Isosceles"),
            new TestCase(2, 3, 2, "Isosceles"),
            new TestCase(3, 2, 2, "Isosceles"),
            new TestCase(100, 100, 1, "Isosceles"),

            // Valid triangles: scalene
            new TestCase(2, 3, 4, "Scalene"),
            new TestCase(98, 99, 100, "Scalene"),
            new TestCase(5, 6, 8, "Scalene"),

            // Not a triangle by inequality
            new TestCase(1, 1, 2, "Not a Triangle"),
            new TestCase(2, 3, 5, "Not a Triangle"),
            new TestCase(1, 2, 3, "Not a Triangle"),
            new TestCase(100, 1, 1, "Not a Triangle"),

            // Invalid input values
            new TestCase(0, 10, 10, "Invalid Input"),
            new TestCase(101, 50, 50, "Invalid Input"),
            new TestCase(-1, 10, 10, "Invalid Input"),
            new TestCase(10, 10, 0, "Invalid Input"),
            new TestCase(10, 10, 101, "Invalid Input")
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Triangle Classifier Test Suite");
        System.out.println("------------------------------");

        for (int i = 0; i < cases.length; i++) {
            TestCase test = cases[i];
            String actual = TriangleClassifier.classify(test.a, test.b, test.c);
            boolean ok = actual.equals(test.expected);
            if (ok) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf(
                "Test %02d: (%d, %d, %d) => expected='%s', actual='%s' %s%n",
                i + 1,
                test.a,
                test.b,
                test.c,
                test.expected,
                actual,
                ok ? "[PASS]" : "[FAIL]"
            );
        }

        System.out.println();
        System.out.printf("Total: %d, Passed: %d, Failed: %d%n", cases.length, passed, failed);
    }
}
