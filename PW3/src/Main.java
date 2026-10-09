import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Введіть друге число: ");
            double b = Double.parseDouble(scanner.nextLine().trim());

            System.out.println("Оберіть операцію:");
            System.out.println("+ — додавання");
            System.out.println("- — віднімання");
            System.out.println("* — множення");
            System.out.println("/ — ділення");
            System.out.print("Ваша операція: ");

            String operation = scanner.nextLine().trim();
            double result;

            switch (operation) {
                case "+":
                    result = calculator.add(a, b);
                    break;
                case "-":
                    result = calculator.subtract(a, b);
                    break;
                case "*":
                    result = calculator.multiply(a, b);
                    break;
                case "/":
                    result = calculator.divide(a, b);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Невідома операція: " + operation
                    );
            }

            System.out.println("Результат: " + result);

        } catch (NumberFormatException e) {
            System.out.println(
                    "Помилка: введено некоректне число."
            );

        } catch (InvalidInputException e) {
            System.out.println(
                    "Помилка введення: " + e.getMessage()
            );

        } catch (ArithmeticException e) {
            System.out.println(
                    "Арифметична помилка: " + e.getMessage()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Помилка: " + e.getMessage()
            );

        } finally {
            System.out.println(
                    "Обробку запиту завершено."
            );
            scanner.close();
        }
    }
}