package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTopExpensesTest {

    @Test
    void testFindTopTenExpenses() {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            transactions.add(
                    new Transaction(
                            "01-02-2024",
                            -1000.0 * i,
                            "Витрата " + i
                    )
            );
        }

        transactions.add(
                new Transaction("01-02-2024", 50000.0, "Дохід")
        );

        List<Transaction> topExpenses =
                TransactionAnalyzer.findTopExpenses(transactions);

        assertEquals(10, topExpenses.size());

        assertEquals(-12000.0, topExpenses.get(0).getAmount());
        assertEquals(-11000.0, topExpenses.get(1).getAmount());

        assertEquals(-3000.0, topExpenses.get(9).getAmount());

        assertTrue(
                topExpenses.stream().allMatch(t -> t.getAmount() < 0)
        );
    }
}
