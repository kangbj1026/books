package com.book.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.book.common.constant.ErrorCode;
import com.book.common.exception.BusinessException;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.List;

public class StringListConverter implements AttributeConverter<List, String>
{
	private final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
			.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

	@Override
	public String convertToDatabaseColumn(List strings)
	{

		if (strings == null)
		{
			return null;
		}

		try
		{
			return mapper.writeValueAsString(strings);
		}
		catch (JsonProcessingException e)
		{
			throw new BusinessException(ErrorCode.UNABLE_TO_CONVERT_LIST_TO_STRING);
		}
	}

	@Override
	public List<?> convertToEntityAttribute(String s)
	{

		if (s == null || s.isEmpty())
		{
			return null;
		}

		try
		{
			return mapper.readValue(s, List.class);
		}
		catch (IOException e)
		{
			throw new BusinessException(ErrorCode.UNABLE_TO_CONVERT_LIST_TO_STRING);
		}
	}
}