package com.challenge.transactions.helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FormatHelpers {

	@SuppressWarnings("deprecation")
	public static BigDecimal formatBigDecimal(BigDecimal value) {
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		new DecimalFormat("#.00").format(value);
		return value;
	}

	public static String formatJsonString(final Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static JsonNode formatJsonNode(final String content) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
			objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
			return objectMapper.readTree(content);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
