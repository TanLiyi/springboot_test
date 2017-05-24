package com.liyi.utils;

import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.LookupTranslator;

public class EscapeUtils {
	public static final CharSequenceTranslator ESCAPE_SQL_LIKE = new LookupTranslator(
			new String[][] { { "%", "\\%" }, { "\\", "\\\\" }, { "_", "\\_" } });

	public static String escapeSqlLike(String input) {
		return ESCAPE_SQL_LIKE.translate(input);
	}
}
