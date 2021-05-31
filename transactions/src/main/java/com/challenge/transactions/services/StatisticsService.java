package com.challenge.transactions.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.transactions.models.Statistics;
import com.challenge.transactions.models.Transaction;

@Service
public class StatisticsService {
	@Autowired
	private TransactionService transactionService;

	public Statistics calculateStatistics() {
		List<Transaction> toProcess = transactionService.getAllValidTransactions();
		Statistics toReturn = new Statistics();
		if (!toProcess.isEmpty()) {
			BigDecimal sum = new BigDecimal("0.0");
			BigDecimal avg = new BigDecimal("0.0");
			BigDecimal count = new BigDecimal(toProcess.size());
			Comparator<Transaction> comparator = Comparator.comparing(Transaction::getAmount);
			Collections.sort(toProcess, comparator);
			for (Transaction transaction : toProcess) {
				BigDecimal sumToAdd = new BigDecimal(transaction.getAmount());
				sum = sum.add(sumToAdd);
			}
			toReturn.setSum(sum);
			avg = sum.divide(count);
			toReturn.setAvg(avg);
			toReturn.setCount(Long.valueOf(toProcess.size()));
			toReturn.setMax(new BigDecimal(toProcess.get(0).getAmount()));
			toReturn.setMin(new BigDecimal(toProcess.get(toProcess.size() - 1).getAmount()));
		}
		return toReturn;
	}
}
