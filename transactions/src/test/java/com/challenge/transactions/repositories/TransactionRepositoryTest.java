package com.challenge.transactions.repositories;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.challenge.transactions.models.Transaction;
import com.challenge.transactions.models.TransactionDataSource;
import com.google.common.collect.Lists;

@RunWith(ConcurrentTestRunner.class)
@SpringBootTest
class TransactionRepositoryTest {
	@MockBean
	private TransactionDataSource dataSource;

	private static Stream<Transaction> getTransactionValue() {
		Instant timestamp = Instant.now();
		return Stream.of(new Transaction("18.3343", timestamp));
	}

	@Test
	void findAllTransactionsYoungerThan60SecondsFromNow_Success_ListNotEmpty() {
		Instant timestamp = Instant.now();
		Transaction firstTransaction = new Transaction("18.3343", timestamp);
		Transaction secondTransaction = new Transaction("15.3343", timestamp);
		doReturn(Lists.newArrayList(firstTransaction, secondTransaction)).when(dataSource).getTransactions();

		for (Transaction transaction : dataSource.getTransactions()) {
			if (transaction.isAfterNow() || transaction.isOlderThan60Seconds()) {
				fail();
			}
		}
	}

	@ParameterizedTest
	@MethodSource("getTransactionValue")
	void saveTransaction_Success_TransactionAsAParameter(Transaction transaction) {
		Mockito.doNothing().when(dataSource).addTransaction(transaction);
	}

}
