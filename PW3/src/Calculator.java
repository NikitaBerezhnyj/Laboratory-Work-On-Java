public class Calculator {

    private void validateInput(double a, double b)
            throws InvalidInputException {
        if (!Double.isFinite(a) || !Double.isFinite(b)) {
            throw new InvalidInputException(
                    "Вхідні значення повинні бути скінченними числами."
            );
        }
    }

    public double add(double a, double b)
            throws InvalidInputException {
        validateInput(a, b);
        return a + b;
    }

    public double subtract(double a, double b)
            throws InvalidInputException {
        validateInput(a, b);
        return a - b;
    }

    public double multiply(double a, double b)
            throws InvalidInputException {
        validateInput(a, b);
        return a * b;
    }

    public double divide(double a, double b)
            throws InvalidInputException, ArithmeticException {
        validateInput(a, b);

        if (b == 0.0) {
            throw new ArithmeticException(
                    "Ділення на нуль неможливе."
            );
        }

        return a / b;
    }
}