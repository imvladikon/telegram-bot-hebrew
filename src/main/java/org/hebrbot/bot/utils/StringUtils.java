package org.hebrbot.bot.utils;

public class StringUtils {
	public static String removeAll(final String s, String... strings) {
		String result = s;
		for (String str : strings) {
			result = result.replace(str, "").trim();
			result = result.replace(str.toLowerCase(), "").trim();
			result = result.replace(str.toUpperCase(), "").trim();
		}
		return result.trim();
	}

	public static String removeNiqqud(final String word) {
		final int length = word.length();
		final StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			if (isNiqqudChar(word.charAt(i))) {
				continue;
			}
			sb.append(word.charAt(i));
		}
		return sb.toString();
	}

	public static boolean isHebrewLetter(char c) {
		return ((c >= 1488) && (c <= 1514));
	}

	public static boolean isFinalHebrewLetter(char c) {
		return (c == 1507 || c == 1498 || c == 1501 || c == 1509 || c == 1503);
	}

	public static boolean isNiqqudChar(char c) {
		return ((c >= 1456) && (c <= 1465))
			|| (c == '\u05C1' || c == '\u05C2' || c == '\u05BB' || c == '\u05BC');
	}

    public static boolean isHebrew(String word) {
        for (char ch : word.toCharArray()) {
            if (isHebrewLetter(ch)) {
                return true;
            }
        }
        return false;
    }
}
