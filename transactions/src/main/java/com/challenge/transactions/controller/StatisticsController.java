package com.challenge.transactions.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactions.models.Statistics;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@GetMapping
	ResponseEntity<Statistics> get() {
		Statistics statistics = new Statistics(new BigDecimal("1000.43"), new BigDecimal("100.53"),
				new BigDecimal("200000.49"), new BigDecimal("50.23"), Long.valueOf(10));

		return ResponseEntity.status(HttpStatus.OK).body(statistics);

	}

}
