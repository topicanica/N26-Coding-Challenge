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
		Integer c = 10;
		BigDecimal sum = new BigDecimal(Double.toString(1000.00));
		BigDecimal avg = new BigDecimal(Double.toString(100.534));
		BigDecimal max = new BigDecimal(Double.toString(200000.494));
		BigDecimal min = new BigDecimal(Double.toString(50.233));
		Statistics stats = new Statistics(sum, avg, max, min, c.longValue());
		return ResponseEntity.status(HttpStatus.OK).body(stats);
	}

}
