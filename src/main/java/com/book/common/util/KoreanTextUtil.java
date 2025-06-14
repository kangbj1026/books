package com.book.common.util;

public class KoreanTextUtil
{
	private static final int HANGUL_START = 0xAC00;    // '가'
	private static final int HANGUL_END = 0xD7A3;      // '힣'
	private static final int HANGUL_BASE = 0xAC00;
	private static final int CHOSUNG_BASE = 21 * 28;
	private static final int JUNGSUNG_BASE = 28;


	private static final char[] CHOSUNG_LIST = {
			'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ',
			'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
	};

	private static final char[] JONGSUNG_LIST = {
			' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ',
			'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ',
			'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
	};

	// 초성 추출
	public static String getChosung(String text) {
		if (text == null || text.isEmpty()) {
			return "";
		}

		StringBuilder result = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (c >= HANGUL_START && c <= HANGUL_END) {
				int chosungIndex = (c - HANGUL_BASE) / CHOSUNG_BASE;
				result.append(CHOSUNG_LIST[chosungIndex]);
			}
		}
		return result.toString();
	}

	// 종성 추출
	public static String getJongsung(String text) {
		if (text == null || text.isEmpty()) {
			return "";
		}

		StringBuilder result = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (c >= HANGUL_START && c <= HANGUL_END) {
				int jongsungIndex = (c - HANGUL_BASE) % JUNGSUNG_BASE;
				if (jongsungIndex != 0) {  // 종성이 있는 경우만
					result.append(JONGSUNG_LIST[jongsungIndex]);
				}
			}
		}
		return result.toString();
	}

	// 문자가 자음인지 확인
	public static boolean isConsonant(String consonant)
	{
		if (consonant.toCharArray().length == 1)
		{
			char ch = consonant.toCharArray()[0];
			return (ch >= 'ㄱ' && ch <= 'ㅎ') || (ch >= 'ㄲ' && ch <= 'ㅆ');
		}
		return false;
	}

	// 초성/종성 포함 여부 확인
	public static boolean containsConsonant(String source, String consonant) {
		String chosung = getChosung(source);
		String jongsung = getJongsung(source);
		return chosung.contains(consonant) || jongsung.contains(consonant);
	}
}
