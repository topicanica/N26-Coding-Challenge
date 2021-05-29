package com.challenge.transactions.models;

import java.time.Duration;
import java.time.Instant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Transaction {

	@NotNull
	@NotEmpty
	private String amount;
	// @NotNull
	private Instant timestamp;

	public Transaction(@NotNull @NotEmpty String amount, Instant timestamp) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public Boolean isOlderThan60Seconds() {

		Instant now = Instant.now();
		Duration result = Duration.between(this.timestamp, now);
		if (result.toSeconds() > 60)
			return true;
		return false;
	}

	public Boolean isAfterNow() {

		Instant now = Instant.now();
		Duration result = Duration.between(this.timestamp, now);
		System.out.print(result.toSeconds());
		if (result.isNegative())
			return true;
		return false;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

}
