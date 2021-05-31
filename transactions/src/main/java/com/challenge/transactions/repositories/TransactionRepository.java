package com.challenge.transactions.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.transactions.models.Transaction;
import com.challenge.transactions.models.TransactionDataSource;

@Component
public class TransactionRepository {
	@Autowired
	private TransactionDataSource dataSource;

	public List<Transaction> findAllTransactionsYoungerThan60SecondsFromNow() {
		List<Transaction> toReturn = new ArrayList<Transaction>();

		if (!dataSource.transactions.isEmpty()) {
			for (Transaction transaction : dataSource.transactions) {
				if (!(transaction.isAfterNow()) && !(transaction.isOlderThan60Seconds())) {
					toReturn.add(transaction);
				}
			}
		}
		return toReturn;
	}

	public void saveTransaction(Transaction transaction) {
		dataSource.addTransaction(transaction);
	}
}
