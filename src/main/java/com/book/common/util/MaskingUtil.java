package com.book.common.util;

public class MaskingUtil
{
	// MaskingProcessing
	public static String MP(String str)
	{
		return str.substring(0, 2) + "*".repeat(str.length() - 4) + str.substring(str.length() - 2);
	}
}
