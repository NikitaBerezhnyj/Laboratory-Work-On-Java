package org.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private TransactionAnalyzer() {
    }

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }

    public static int countTransactionsByMonth(
            List<Transaction> transactions,
            String monthYear
    ) {
        int count = 0;

        for (Transaction transaction : transactions) {
            LocalDate date = parseDate(transaction.getDate());
            String transactionMonthYear =
                    date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }

        return count;
    }

    public static List<Transaction> findTopExpenses(
            List<Transaction> transactions
    ) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Transaction findLargestExpense(
            List<Transaction> transactions,
            LocalDate startDate,
            LocalDate endDate
    ) {
        validatePeriod(startDate, endDate);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> isWithinPeriod(t, startDate, endDate))
                .min(Comparator.comparingDouble(Transaction::getAmount))
                .orElse(null);
    }

    public static Transaction findSmallestExpense(
            List<Transaction> transactions,
            LocalDate startDate,
            LocalDate endDate
    ) {
        validatePeriod(startDate, endDate);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> isWithinPeriod(t, startDate, endDate))
                .max(Comparator.comparingDouble(Transaction::getAmount))
                .orElse(null);
    }

    public static Map<String, Double> calculateExpensesByCategory(
            List<Transaction> transactions
    ) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        TreeMap::new,
                        Collectors.summingDouble(
                                t -> Math.abs(t.getAmount())
                        )
                ));
    }

    public static Map<String, Double> calculateExpensesByMonth(
            List<Transaction> transactions
    ) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> YearMonth.from(parseDate(t.getDate()))
                                .format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        TreeMap::new,
                        Collectors.summingDouble(
                                t -> Math.abs(t.getAmount())
                        )
                ));
    }

    private static boolean isWithinPeriod(
            Transaction transaction,
            LocalDate startDate,
            LocalDate endDate
    ) {
        LocalDate date = parseDate(transaction.getDate());

        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    private static void validatePeriod(
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Дати періоду не можуть бути null");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Початок періоду не може бути пізніше його завершення"
            );
        }
    }
}
