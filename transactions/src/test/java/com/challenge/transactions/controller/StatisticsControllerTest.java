package com.challenge.transactions.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.challenge.transactions.helpers.FormatHelpers;
import com.challenge.transactions.models.Statistics;
import com.challenge.transactions.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.JsonNode;

@RunWith(ConcurrentTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatisticsControllerTest {
	@Autowired
	Validator validator;
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionRepository transactionService;

	@Test
	void getStatistics_IsAsserted_IfSuccess() throws Exception {
		Statistics statistics = new Statistics(new BigDecimal("33.67"), new BigDecimal("16.83"),
				new BigDecimal("15.33"), new BigDecimal("18.33"), Long.valueOf(2));

		MvcResult result = mockMvc.perform(get("/statistics").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		JsonNode statisticsAsJson = FormatHelpers.formatJsonNode(content);
		assertEquals(statisticsAsJson.get("sum").decimalValue(), statistics.getSum());
		assertEquals(statisticsAsJson.get("avg").decimalValue(), statistics.getAvg());
		assertEquals(statisticsAsJson.get("max").decimalValue(), statistics.getMax());
		assertEquals(statisticsAsJson.get("min").decimalValue(), statistics.getMin());
		assertEquals((Long) statisticsAsJson.get("count").longValue(), statistics.getCount());
	}

}
