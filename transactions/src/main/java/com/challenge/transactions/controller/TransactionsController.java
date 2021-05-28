package com.challenge.transactions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactions.models.Transaction;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@PostMapping
	ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}

	@DeleteMapping
	ResponseEntity<?> delete() {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
