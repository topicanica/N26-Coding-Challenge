package com.challenge.transactions.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TransactionDataSource {
	public List<Transaction> transactions = new ArrayList<Transaction>();

	public TransactionDataSource() {
		Instant timestamp = Instant.now();
		Transaction firstTransaction = new Transaction("18.3343", timestamp);
		Transaction secondTransaction = new Transaction("15.3343", timestamp);
		this.transactions.add(firstTransaction);
		this.transactions.add(secondTransaction);
	}

	public Transaction getLastTransaction() {
		return this.transactions.get(this.transactions.size() - 1);
	}

	public TransactionDataSource(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
