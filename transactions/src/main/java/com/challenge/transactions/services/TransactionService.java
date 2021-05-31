package com.challenge.transactions.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.transactions.models.Transaction;
import com.challenge.transactions.repositories.TransactionRepository;

@Component
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getAllValidTransactions() {
		return transactionRepository.findAllTransactionsYoungerThan60SecondsFromNow();
	}

	public void saveTransaction(Transaction transaction) {
		transactionRepository.saveTransaction(transaction);
	}
}
