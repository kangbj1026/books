package com.book.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils
{

	public static LocalDateTime convertToLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static String formatDate(LocalDateTime localDateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return localDateTime.format(formatter);
	}

	public static LocalDateTime formatDate(String dateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(dateTime, formatter);
	}

	public static String formatYYYYMMDD(LocalDateTime localDateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDateTime.format(formatter);
	}

	public static String formatYYYYMMDD(LocalDate localDateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDateTime.format(formatter);
	}

	public static String formatYYYYMM(LocalDateTime localDateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		return localDateTime.format(formatter);
	}

	public static String koreanFormatDate(LocalDateTime localDateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 (E)");
		return localDateTime.format(formatter);
	}

	public static String MMddFormatDate(LocalDateTime localDateTime)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일");
		return localDateTime.format(formatter);
	}

}
