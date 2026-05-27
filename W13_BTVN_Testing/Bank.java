public final class Bank {
    public static final String INVALID_INPUT = "Invalid Input";
    public static final String APPROVE = "APPROVE";
    public static final String MANUAL_REVIEW = "MANUAL REVIEW";
    public static final String REJECT = "REJECT";

    public static final String HIGH = "High";
    public static final String MEDIUM = "Medium";
    public static final String LOW = "Low";

    private Bank() {
    }

    private static boolean hasOneDecimalPlace(double value) {
        return Math.abs(value * 10 - Math.round(value * 10)) < 1e-9;
    }

    public static boolean validateInputs(int age, double income, int creditScore, String employment) {
        if (age < 18 || age > 65) {
            return false;
        }

        if (!hasOneDecimalPlace(income) || income < 5.0 || income > 500.0) {
            return false;
        }

        if (creditScore < 300 || creditScore > 850) {
            return false;
        }

        return "C".equals(employment) || "F".equals(employment);
    }

    public static String classifyRisk(int creditScore) {
        if (creditScore >= 300 && creditScore <= 500) {
            return HIGH;
        }
        if (creditScore >= 501 && creditScore <= 700) {
            return MEDIUM;
        }
        return LOW;
    }

    public static String evaluateLoan(int age, double income, int creditScore, String employment) {
        if (!validateInputs(age, income, creditScore, employment)) {
            return INVALID_INPUT;
        }

        String risk = classifyRisk(creditScore);
        if (HIGH.equals(risk)) {
            return REJECT;
        }

        if (income < 15.0) {
            if ("C".equals(employment) && LOW.equals(risk)) {
                return MANUAL_REVIEW;
            }
            return REJECT;
        }

        if ("C".equals(employment)) {
            return APPROVE;
        }
        return MANUAL_REVIEW;
    }
}
