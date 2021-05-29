package com.challenge.transactions.controller;

import org.springframework.boot.json.JsonParseException;
import org.springframework.expression.ParseException;
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

		if (transaction.isOlderThan60Seconds())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		else if (transaction.isAfterNow())
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		try {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (JsonParseException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

	}

	@DeleteMapping
	ResponseEntity<?> delete() {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
