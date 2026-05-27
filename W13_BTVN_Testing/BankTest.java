public final class BankTest {
    private static int passed = 0;
    private static int total = 0;

    private BankTest() {
    }

    public static void main(String[] args) {
        testAgeBoundaries();
        testIncomeBoundaries();
        testIncomeMustHaveOneDecimalPlace();
        testCreditScoreBoundaries();
        testInvalidEmployment();
        testDecisionTableRules();

        System.out.printf("Passed %d/%d tests.%n", passed, total);
    }

    private static void assertEquals(String name, Object expected, Object actual) {
        total++;
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            passed++;
            System.out.println("[PASS] " + name);
            return;
        }
        throw new AssertionError(
            "[FAIL] " + name + " expected: " + expected + ", actual: " + actual
        );
    }

    private static void testAgeBoundaries() {
        assertEquals("TC01 age below min", Bank.INVALID_INPUT, Bank.evaluateLoan(17, 15.0, 701, "C"));
        assertEquals("TC02 age at min", Bank.APPROVE, Bank.evaluateLoan(18, 15.0, 701, "C"));
        assertEquals("TC03 age above min", Bank.APPROVE, Bank.evaluateLoan(19, 15.0, 701, "C"));
        assertEquals("TC04 age below max", Bank.APPROVE, Bank.evaluateLoan(64, 15.0, 701, "C"));
        assertEquals("TC05 age at max", Bank.APPROVE, Bank.evaluateLoan(65, 15.0, 701, "C"));
        assertEquals("TC06 age above max", Bank.INVALID_INPUT, Bank.evaluateLoan(66, 15.0, 701, "C"));
    }

    private static void testIncomeBoundaries() {
        assertEquals("TC07 income below min", Bank.INVALID_INPUT, Bank.evaluateLoan(30, 4.9, 701, "C"));
        assertEquals("TC08 income at min", Bank.MANUAL_REVIEW, Bank.evaluateLoan(30, 5.0, 701, "C"));
        assertEquals("TC09 income above min", Bank.MANUAL_REVIEW, Bank.evaluateLoan(30, 5.1, 701, "C"));
        assertEquals("TC10 income just below 15", Bank.MANUAL_REVIEW, Bank.evaluateLoan(30, 14.9, 701, "C"));
        assertEquals("TC11 income at 15", Bank.APPROVE, Bank.evaluateLoan(30, 15.0, 701, "C"));
        assertEquals("TC12 income above 15", Bank.APPROVE, Bank.evaluateLoan(30, 15.1, 701, "C"));
        assertEquals("TC13 income below max", Bank.APPROVE, Bank.evaluateLoan(30, 499.9, 701, "C"));
        assertEquals("TC14 income at max", Bank.APPROVE, Bank.evaluateLoan(30, 500.0, 701, "C"));
        assertEquals("TC15 income above max", Bank.INVALID_INPUT, Bank.evaluateLoan(30, 500.1, 701, "C"));
    }

    private static void testIncomeMustHaveOneDecimalPlace() {
        assertEquals("TC16 invalid income precision", Bank.INVALID_INPUT, Bank.evaluateLoan(30, 15.55, 701, "C"));
    }

    private static void testCreditScoreBoundaries() {
        assertEquals("TC17 score below min", Bank.INVALID_INPUT, Bank.evaluateLoan(30, 15.0, 299, "C"));
        assertEquals("TC18 score at min", Bank.REJECT, Bank.evaluateLoan(30, 15.0, 300, "C"));
        assertEquals("TC19 classify high upper", Bank.HIGH, Bank.classifyRisk(500));
        assertEquals("TC20 classify medium lower", Bank.MEDIUM, Bank.classifyRisk(501));
        assertEquals("TC21 classify medium upper", Bank.MEDIUM, Bank.classifyRisk(700));
        assertEquals("TC22 classify low lower", Bank.LOW, Bank.classifyRisk(701));
        assertEquals("TC23 score at max", Bank.APPROVE, Bank.evaluateLoan(30, 15.0, 850, "C"));
        assertEquals("TC24 score above max", Bank.INVALID_INPUT, Bank.evaluateLoan(30, 15.0, 851, "C"));
    }

    private static void testInvalidEmployment() {
        assertEquals("TC25 invalid employment", Bank.INVALID_INPUT, Bank.evaluateLoan(30, 15.0, 701, "X"));
    }

    private static void testDecisionTableRules() {
        assertEquals("TC26 high risk contract", Bank.REJECT, Bank.evaluateLoan(30, 100.0, 500, "C"));
        assertEquals("TC27 high risk freelance", Bank.REJECT, Bank.evaluateLoan(30, 100.0, 500, "F"));
        assertEquals("TC28 low risk contract under 15", Bank.MANUAL_REVIEW, Bank.evaluateLoan(30, 14.9, 701, "C"));
        assertEquals("TC29 low risk freelance under 15", Bank.REJECT, Bank.evaluateLoan(30, 14.9, 701, "F"));
        assertEquals("TC30 medium risk contract under 15", Bank.REJECT, Bank.evaluateLoan(30, 14.9, 700, "C"));
        assertEquals("TC31 medium risk freelance under 15", Bank.REJECT, Bank.evaluateLoan(30, 14.9, 700, "F"));
        assertEquals("TC32 low risk contract over 15", Bank.APPROVE, Bank.evaluateLoan(30, 15.0, 701, "C"));
        assertEquals("TC33 low risk freelance over 15", Bank.MANUAL_REVIEW, Bank.evaluateLoan(30, 15.0, 701, "F"));
        assertEquals("TC34 medium risk contract over 15", Bank.APPROVE, Bank.evaluateLoan(30, 15.0, 700, "C"));
        assertEquals("TC35 medium risk freelance over 15", Bank.MANUAL_REVIEW, Bank.evaluateLoan(30, 15.0, 700, "F"));
    }
}
