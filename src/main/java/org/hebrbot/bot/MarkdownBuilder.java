package org.hebrbot.bot;

import org.pegdown.PegDownProcessor;

public final class MarkdownBuilder {

	/*
	 * https://core.telegram.org/bots/api#markdownv2-style
	 * Please note:
	 * Any character between 1 and 126 inclusively can be escaped anywhere with a preceding '\' character, in which case it
	 * is treated as an ordinary character and not a part of the markup.
	 * Inside pre and code entities, all '`‘ and ’\‘ characters must be escaped with a preceding ’\' character.
	 * Inside (...) part of inline link definition, all ')‘ and ’\‘ must be escaped with a preceding ’\' character.
	 * In all other places characters '_‘, ’*‘, ’[‘, ’]‘, ’(‘, ’)‘, ’~‘, ’`‘, ’>‘, ’#‘, ’+‘, ’-‘, ’=‘, ’|‘, ’{‘, ’}‘, ’.‘,
	 * ’!‘ must be escaped with the preceding character ’\'.
	 * In case of ambiguity between italic and underline entities ‘__’ is always greadily treated from left to right as
	 * beginning or end of underline entity, so instead of ___italic underline___ use ___italic underline_\r__, where \r is
	 * a character with code 13, which will be ignored.
	 */

	private final StringBuilder stringBuilder;
	private final static char[] ESCAPING_CHARS = new char[] {	'_',
										'*',
										'[',
										']',
										'(',
										')',
										'~',
										'`',
										'>',
										'#',
										'+',
										'-',
										'=',
										'|',
										'{',
										'}',
										'.',
										'!' };

	public MarkdownBuilder() {
		this.stringBuilder = new StringBuilder();
	}

	public MarkdownBuilder(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}

	public MarkdownBuilder clear() {
		stringBuilder.setLength(0);
		return this;
	}

	public MarkdownBuilder bold(String text) {
		stringBuilder.append("*");
		stringBuilder.append(escape(text));
		stringBuilder.append("*");
		return this;
	}

	public MarkdownBuilder italic(String text) {
		stringBuilder.append("_");
		stringBuilder.append(escape(text));
		stringBuilder.append("_");
		return this;
	}

	public MarkdownBuilder underline(String text) {
		stringBuilder.append("__");
		stringBuilder.append(escape(text));
		stringBuilder.append("__");
		return this;
	}

	public MarkdownBuilder strikethrough(String text) {
		stringBuilder.append("~");
		stringBuilder.append(escape(text));
		stringBuilder.append("~");
		return this;
	}

	public MarkdownBuilder formatted(String text) {
		stringBuilder.append("`");
		stringBuilder.append(escape(text));
		stringBuilder.append("`");
		return this;
	}

	/**
	 * @param url
	 * @param caption
	 * @return [inline URL](http://www.example.com/)
	 */
	public MarkdownBuilder url(String url, String caption) {
		stringBuilder.append("[");
		stringBuilder.append(caption);
		stringBuilder.append("]");
		stringBuilder.append("(");
		stringBuilder.append(url);
		stringBuilder.append(")");
		return this;
	}

	public MarkdownBuilder preFormatCodeText(String text, String lang) {
		stringBuilder.append("```");
		stringBuilder.append(lang);
		stringBuilder.append("\n");
		stringBuilder.append(escape(text));
		stringBuilder.append("\n");
		stringBuilder.append("```");
		return this;
	}

	public MarkdownBuilder preFormatText(String text) {
		return preFormatCodeText(text, "");
	}

	public String toHtml() {
		return new PegDownProcessor().markdownToHtml(toString());
	}

	public String escape(String text) {
		String result = text;
		for (int i = 0 ; i < ESCAPING_CHARS.length ; i++){
			final String s = Character.toString(ESCAPING_CHARS[i]);
			if(result.contains(s)){
				result = result.replace(s,"\\"+s);
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return this.stringBuilder.toString();
	}
}
