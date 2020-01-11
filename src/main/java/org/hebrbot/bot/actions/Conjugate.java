package org.hebrbot.bot.actions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hebrbot.bot.model.*;
import org.hebrbot.bot.scrapers.PealimScraper;
import org.hebrbot.bot.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conjugate {

	HebrewPronouns pronoun;
	VerbForm form;
	GrammaticalPerson person;
	GrammaticalNumber number;
	GrammaticalGender gender;
	String word;

	//https://en.wikipedia.org/wiki/Modern_Hebrew_verb_conjugation

	public String get() {
		PealimScraper p = new PealimScraper();
		final HebrewVerb hebrewVerb = p.search(word);
		String rows = "";
		final List<HebrewVerbInflection> verbInflections = hebrewVerb.getInflections()
				.stream()
				.filter(i -> i.getForm() == form || form == null)
				.filter(i -> i.getPronoun() == pronoun || pronoun == null)
				.filter(i -> i.getPerson() == person || person == null)
				.filter(i -> i.getNumber() == number || number == null)
				.filter(i -> i.getGender() == gender || gender == null)
				.collect(Collectors.toList());
		for (HebrewVerbInflection c : verbInflections) {
			rows += "|" + c.getNameNekudot() + "|" + c.getMeaning() + "|" + c.getTranscription()
				+ "|\n";
		}
		return "<pre>\n" +
//				"|   hebrew     |     translation     |  transcription   |\n"
//			+ "|----------|:-------------:|------:|\n" +
				rows
			+ "</pre>".replaceAll("\\|", "\\\\|").replaceAll("\\|", "\\\\|");
	}

	//TODO: filter table by pronouns and time
	//TODO: hunspell, lemmatize

	public static String get(String word) {
		return get(word, VerbForm.None);
	}

	public static String get(String word, VerbForm form) {
		PealimScraper p = new PealimScraper();
		final HebrewVerb hebrewVerb = p.search(word);
		String rows = "";
		for (HebrewVerbInflection c : hebrewVerb.getInflections()) {
			rows += "|" + c.getNameNekudot() + "|" + c.getMeaning() + "|" + c.getTranscription()
				+ "|\n";
		}
		return "<pre>\n" + "| Tables   |      Are      |  Cool |\n"
			+ "|----------|:-------------:|------:|\n" + rows
			+ "</pre>".replaceAll("\\|", "\\\\|").replaceAll("\\|", "\\\\|");
	}
}
