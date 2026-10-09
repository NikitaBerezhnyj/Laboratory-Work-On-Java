package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionCSVReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void testReadTransactionsFromCSV() throws IOException {
        String csvContent =
                "01-02-2024,-1500.0,Їжа\n" +
                        "02-02-2024,3000.0,Зарплата\n" +
                        "03-02-2024,-500.0,Транспорт\n";

        Path csvFile = tempDir.resolve("transactions.csv");
        Files.writeString(csvFile, csvContent);

        String filePath = csvFile.toUri().toURL().toString();

        List<Transaction> transactions =
                TransactionCSVReader.readTransactions(filePath);

        assertEquals(3, transactions.size());

        assertEquals("01-02-2024", transactions.get(0).getDate());
        assertEquals(-1500.0, transactions.get(0).getAmount());
        assertEquals("Їжа", transactions.get(0).getDescription());

        assertEquals(3000.0, transactions.get(1).getAmount());
        assertEquals("Транспорт", transactions.get(2).getDescription());
    }
}

