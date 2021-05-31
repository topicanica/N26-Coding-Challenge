package com.challenge.transactions.models;

import java.math.BigDecimal;

import com.challenge.transactions.helpers.FormatHelpers;

public class Statistics {
	private BigDecimal sum;
	private BigDecimal avg;
	private BigDecimal max;
	private BigDecimal min;
	private Long count;

	public Statistics(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, Long count) {
		super();
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}

	public Statistics() {
		super();
	}

	public BigDecimal getSum() {
		return this.sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = FormatHelpers.formatBigDecimal(sum);
	}

	public BigDecimal getAvg() {
		return this.avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = FormatHelpers.formatBigDecimal(avg);
	}

	public BigDecimal getMax() {
		return this.max;
	}

	public void setMax(BigDecimal max) {
		this.max = FormatHelpers.formatBigDecimal(max);
	}

	public BigDecimal getMin() {
		return this.min;
	}

	public void setMin(BigDecimal min) {
		this.min = FormatHelpers.formatBigDecimal(min);
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
