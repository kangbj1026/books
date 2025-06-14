package com.book.common.util;

import java.time.LocalDateTime;

public class TimePeriodCalculator {

	public static int calculateIterations(int months)
	{
		return switch (months)
		{
			case 1, 2, 3 -> 1;
			case 4, 5, 6 -> 2;
			case 7, 8, 9 -> 3;
			case 10, 11, 12 -> 4;
			case 13, 14, 15 -> 5;
			case 16, 17, 18 -> 6;
			case 19, 20, 21 -> 7;
			case 22, 23, 24 -> 8;
			case 25, 26, 27 -> 9;
			case 28, 29, 30 -> 10;
			case 31, 32, 33 -> 11;
			case 34, 35, 36 -> 12;
			default -> throw new IllegalArgumentException("유효 하지 않은 월 수");
		};
	}

	public static int calculateDates(int months)
	{
		return switch (months)
		{
			case 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 -> 30;
			case 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 -> 60;
			case 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 -> 90;
			default -> months;
		};
	}

	public static String takingTimeOut(LocalDateTime date)
	{
		String dates = String.valueOf(date);
		return dates.contains("T") ? dates.split("T")[0] : dates;
	}
}