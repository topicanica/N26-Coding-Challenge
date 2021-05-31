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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.challenge.transactions.helpers.FormatHelpers;
import com.challenge.transactions.models.Transaction;
import com.challenge.transactions.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(ConcurrentTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionsControllerTest {

	@Autowired
	Validator validator;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	private TransactionRepository transactionService;

	@Test
	void postTransactions_IsCreated_IfSuccess() throws Exception {
		Instant timestamp = Instant.now();
		Transaction transactionToPost = new Transaction("12.3343", timestamp);
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(FormatHelpers.formatJsonString(transactionToPost))).andExpect(status().isCreated());
	}

	@Test
	public void testValidationIfAmountIsNull() {
		Instant timestamp = Instant.now();
		Transaction transactionToPost = new Transaction(null, timestamp);
		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transactionToPost);
		assertFalse(constraintViolations.isEmpty());
	}

	@Test
	public void testValidationIfTimestampIsNull() {
		Transaction transactionToPost = new Transaction("12.3343", null);
		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transactionToPost);
		assertFalse(constraintViolations.isEmpty());
	}

	@Test
	public void testValidationIfAmountIsEmpty() {
		Instant timestamp = Instant.now();
		Transaction transactionToPost = new Transaction("", timestamp);
		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transactionToPost);
		assertFalse(constraintViolations.isEmpty());
	}

	@Test
	void postTransaction_NoContent_IfOlderThan60Seconds() throws Exception {
		Instant timestamp = Instant.now().minusSeconds(80);
		Transaction transactionToPost = new Transaction("12.3343", timestamp);
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(FormatHelpers.formatJsonString(transactionToPost))).andExpect(status().isNoContent());
	}

	@Test
	void postTransaction_IsUnprocessable_IfAfterNow() throws Exception {
		Instant timestamp = Instant.now().plusSeconds(120);
		Transaction transactionToPost = new Transaction("12.3343", timestamp);
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content(FormatHelpers.formatJsonString(transactionToPost)))
				.andExpect(status().isUnprocessableEntity());
	}

	@Test
	void postTransactions_BadRequest_IfInvalidJSON() throws Exception {
		Instant timestamp = Instant.now();
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content("{\"amount\" , \"timestamp\":\"" + timestamp + "\"}")).andExpect(status().isBadRequest());
	}

	@Test
	void postTransactions_IsUnprocessable_IfNotParsable() throws Exception {
		Instant timestamp = Instant.now();
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
				.content("{\"amount\": \"random\", \"timestamp\":\"" + timestamp + "\"}"))
				.andExpect(status().isUnprocessableEntity());
	}

	@Test
	void deleteTransactions_NoContent_IfSuccess() throws Exception {
		mockMvc.perform(delete("/transactions")).andExpect(status().isNoContent());
	}

}
