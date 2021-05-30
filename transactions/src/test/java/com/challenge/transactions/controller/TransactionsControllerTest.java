package com.challenge.transactions.controller;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.transactions.models.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionsControllerTest {

	@Autowired
	Validator validator;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void testPostStatisticsSuccess() throws Exception {
		// given
		Instant timestamp = Instant.now();
		Transaction transactionToPost = new Transaction("12.3343", timestamp);

		// when + then
		mockMvc.perform(
				post("/transactions").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transactionToPost)))

				// Validate the response code
				.andExpect(status().isCreated());
	}

	@Test
	public void postRequest_InternalServerError_IfAmountIsNull() {
		// given
		Instant timestamp = Instant.now();
		Transaction transactionToPost = new Transaction(null, timestamp);

		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transactionToPost);
		assertFalse(constraintViolations.isEmpty());
	}

	@Test
	public void postRequest_InternalServerError_IfTimestampIsNull() {
		// given
		Instant timestamp = Instant.now();
		Transaction transactionToPost = new Transaction("12.3343", null);

		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transactionToPost);
		assertFalse(constraintViolations.isEmpty());
	}

	@Test
	public void postRequest_InternalServerError_IfAmountIsEmpty() {
		// given
		Instant timestamp = Instant.now();
		Transaction transactionToPost = new Transaction("", timestamp);

		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transactionToPost);
		assertFalse(constraintViolations.isEmpty());
	}

	@Test
	void postTransaction_NoContent_IfOlderThan60Seconds() throws Exception {
		// given
		Instant timestamp = Instant.now().minusSeconds(80);
		Transaction transactionToPost = new Transaction("12.3343", timestamp);

		// when + then
		mockMvc.perform(
				post("/transactions").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transactionToPost)))

				// Validate the response code
				.andExpect(status().isNoContent());

	}

	@Test

	void postTransaction_IsUnprocessable_IfAfterNow() throws Exception {
		// given
		Instant timestamp = Instant.now().plusSeconds(120);
		Transaction transactionToPost = new Transaction("12.3343", timestamp);

		// when + then
		mockMvc.perform(
				post("/transactions").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transactionToPost)))

				// Validate the response code
				.andExpect(status().isUnprocessableEntity());

	}

	@Test
	void postTransactions_BadRequest_IfInvalidJSON() throws Exception {
		Instant timestamp = Instant.now();
		// when + then
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content("{\"amount\" , \"timestamp\":\"" + timestamp + "\"}"))
				// Validate the response code
				.andExpect(status().isBadRequest());
		// System.out.print(Instant.now().toString());

	}

	@Test
	void postTransactions_IsUnprocessable_IfNotParsable() throws Exception {
		Instant timestamp = Instant.now();
		// when + then
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content("{\"amount\": \"random\", \"timestamp\":\"" + timestamp + "\"}"))

				// Validate the response code
				.andExpect(status().isUnprocessableEntity());

	}

	@Test
	void testDeleteStatisticsSuccess() throws Exception {
		mockMvc.perform(delete("/transactions"))

				// Validate the response code
				.andExpect(status().isNoContent());

	}

	static String asJsonString(final Object obj) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			return mapper.writeValueAsString(obj);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
