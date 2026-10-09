package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 =
                new Transaction("2023-01-01", 100.0, "Дохід");

        Transaction transaction2 =
                new Transaction("2023-01-02", -50.0, "Витрата");

        Transaction transaction3 =
                new Transaction("2023-01-03", 150.0, "Дохід");

        List<Transaction> transactions = Arrays.asList(
                transaction1,
                transaction2,
                transaction3
        );

        double result =
                TransactionAnalyzer.calculateTotalBalance(transactions);

        Assertions.assertEquals(
                200.0,
                result,
                "Розрахунок загального балансу неправильний"
        );
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 =
                new Transaction("01-02-2023", 50.0, "Дохід");

        Transaction transaction2 =
                new Transaction("15-02-2023", -20.0, "Витрата");

        Transaction transaction3 =
                new Transaction("05-03-2023", 100.0, "Дохід");

        List<Transaction> transactions = Arrays.asList(
                transaction1,
                transaction2,
                transaction3
        );

        int countFeb =
                TransactionAnalyzer.countTransactionsByMonth(
                        transactions,
                        "02-2023"
                );

        int countMar =
                TransactionAnalyzer.countTransactionsByMonth(
                        transactions,
                        "03-2023"
                );

        Assertions.assertEquals(
                2,
                countFeb,
                "Кількість транзакцій за лютий неправильна"
        );

        Assertions.assertEquals(
                1,
                countMar,
                "Кількість транзакцій за березень неправильна"
        );
    }
}