package com.challenge.transactions.services;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.challenge.transactions.models.Transaction;
import com.challenge.transactions.repositories.TransactionRepository;

@RunWith(ConcurrentTestRunner.class)
@SpringBootTest
class TransactionServiceTest {
	@Mock
	private TransactionRepository transactionRepository;

	private static Stream<Transaction> getTransactionValue() {
		Instant timestamp = Instant.now();
		return Stream.of(new Transaction("18.3343", timestamp));
	}

	@Test
	void getAllVallidTransactions_Success() {
		Instant timestamp = Instant.now();
		Transaction firstTransaction = new Transaction("18.3343", timestamp);
		Transaction secondTransaction = new Transaction("15.3343", timestamp);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(firstTransaction);
		transactions.add(secondTransaction);
		when(transactionRepository.findAllTransactionsYoungerThan60SecondsFromNow()).thenReturn(transactions);
		List<Transaction> returnedTransactions = transactionRepository.findAllTransactionsYoungerThan60SecondsFromNow();
		assertArrayEquals(returnedTransactions.toArray(), transactions.toArray());
	}

	@ParameterizedTest
	@MethodSource("getTransactionValue")
	void saveTransaction_Success(Transaction transaction) {
		Mockito.doNothing().when(transactionRepository).saveTransaction(transaction);
	}

}
