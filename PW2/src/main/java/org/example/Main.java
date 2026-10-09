package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions =
                TransactionCSVReader.readTransactions(filePath);

        double totalBalance =
                TransactionAnalyzer.calculateTotalBalance(transactions);

        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";

        int transactionsCount =
                TransactionAnalyzer.countTransactionsByMonth(
                        transactions,
                        monthYear
                );

        TransactionReportGenerator.printTransactionsCountByMonth(
                monthYear,
                transactionsCount
        );

        List<Transaction> topExpenses =
                TransactionAnalyzer.findTopExpenses(transactions);

        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // Аналіз витрат за період
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 3, 31);

        Transaction largestExpense =
                TransactionAnalyzer.findLargestExpense(
                        transactions,
                        startDate,
                        endDate
                );

        Transaction smallestExpense =
                TransactionAnalyzer.findSmallestExpense(
                        transactions,
                        startDate,
                        endDate
                );

        System.out.println("\nАналіз витрат за період:");
        System.out.println("Період: " + startDate + " — " + endDate);

        if (largestExpense != null) {
            System.out.println("Найбільша витрата: " + largestExpense);
        } else {
            System.out.println("За цей період витрат немає.");
        }

        if (smallestExpense != null) {
            System.out.println("Найменша витрата: " + smallestExpense);
        }

        Map<String, Double> expensesByCategory =
                TransactionAnalyzer.calculateExpensesByCategory(transactions);

        Map<String, Double> expensesByMonth =
                TransactionAnalyzer.calculateExpensesByMonth(transactions);

        TransactionReportGenerator.printExpensesByCategoryReport(
                expensesByCategory
        );

        TransactionReportGenerator.printExpensesByMonthReport(
                expensesByMonth
        );
    }
}
