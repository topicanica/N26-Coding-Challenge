package com.challenge.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactions.models.Statistics;
import com.challenge.transactions.services.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;

	@GetMapping
	ResponseEntity<Statistics> get() {
		Statistics statistics = statisticsService.calculateStatistics();
		return ResponseEntity.status(HttpStatus.OK).body(statistics);
	}
}
