package org.example;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    private static final double AMOUNT_PER_STAR = 1000.0;

    private TransactionReportGenerator() {
    }

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(
            String monthYear,
            int count
    ) {
        System.out.println(
                "Кількість транзакцій за " + monthYear + ": " + count
        );
    }

    public static void printTopExpensesReport(
            List<Transaction> topExpenses
    ) {
        System.out.println("10 найбільших витрат:");

        for (Transaction expense : topExpenses) {
            System.out.println(
                    expense.getDescription() + ": " + expense.getAmount()
            );
        }
    }

    public static void printExpensesByCategoryReport(
            Map<String, Double> expensesByCategory
    ) {
        System.out.println("\nВитрати за категоріями:");

        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            printBar(entry.getKey(), entry.getValue());
        }
    }

    public static void printExpensesByMonthReport(
            Map<String, Double> expensesByMonth
    ) {
        System.out.println("\nВитрати за місяцями:");

        for (Map.Entry<String, Double> entry : expensesByMonth.entrySet()) {
            printBar(entry.getKey(), entry.getValue());
        }
    }

    private static void printBar(String label, double amount) {
        int stars = (int) (amount / AMOUNT_PER_STAR);

        System.out.printf(
                "%-20s %10.2f грн | %s%n",
                label,
                amount,
                "*".repeat(stars)
        );
    }
}
