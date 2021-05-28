package com.challenge.transactions.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.NotNull;

public class Statistics {

	@NotNull
	private BigDecimal sum;

	@NotNull
	private BigDecimal avg;
	@NotNull
	private BigDecimal max;
	@NotNull
	private BigDecimal min;
	@NotNull
	private Long count;

	public Statistics(@NotNull BigDecimal sum, @NotNull BigDecimal avg, @NotNull BigDecimal max,
			@NotNull BigDecimal min, @NotNull Long count) {
		super();
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}

	private static BigDecimal round(BigDecimal value) {
		value = value.setScale(2, RoundingMode.HALF_UP);
		return value;
	}

	public BigDecimal getSum() {
		return round(sum);
	}

	public void setSum(BigDecimal sum) {
		this.sum = round(sum);
	}

	public BigDecimal getAvg() {

		return round(avg);
	}

	public void setAvg(BigDecimal avg) {
		this.avg = round(avg);
	}

	public BigDecimal getMax() {
		return round(max);
	}

	public void setMax(BigDecimal max) {
		this.max = round(max);
	}

	public BigDecimal getMin() {
		return round(min);
	}

	public void setMin(BigDecimal min) {
		this.min = round(min);
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
