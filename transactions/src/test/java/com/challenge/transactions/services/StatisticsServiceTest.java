package com.challenge.transactions.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.challenge.transactions.models.Statistics;

@RunWith(ConcurrentTestRunner.class)
@SpringBootTest
class StatisticsServiceTest {
	@InjectMocks
	private StatisticsService statisticsService;

	@Test
	void calculateStatistics_Success() {
		Statistics statistics = new Statistics(new BigDecimal("33.67"), new BigDecimal("16.83"),
				new BigDecimal("15.33"), new BigDecimal("18.33"), Long.valueOf(2));
		Statistics returnedStatistics = statisticsService.calculateStatistics();
		assertEquals(statistics, returnedStatistics);
	}
}
