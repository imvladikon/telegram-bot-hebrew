package org.hebrbot.bot.scrapers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hebrbot.bot.model.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConjugationRow {
	String menukad;
	String transcription;
	String meaning;
	GrammaticalNumber number;
	GrammaticalGender gender;
	GrammaticalPerson person;
	VerbForm form;
	HebrewPronouns pronoun;
	VerbActivePassive activePassive;

	public static ConjugationRow create(GrammaticalNumber number,
										GrammaticalGender gender,
										GrammaticalPerson person, VerbForm form) {
		return create(number, gender, person, form, VerbActivePassive.Active);
	}

	public static ConjugationRow create(HebrewPronouns pronoun, VerbForm form) {
		return create(pronoun, form, VerbActivePassive.Active);
	}

	public static ConjugationRow create(HebrewPronouns pronoun, VerbForm form, VerbActivePassive activePassive) {
		return ConjugationRow.builder()
				.person(pronoun.getPerson())
				.number(pronoun.getNumber())
				.gender(pronoun.getGender())
				.form(form)
				.pronoun(pronoun)
				.activePassive(activePassive)
				.build();
	}

	public static ConjugationRow create(GrammaticalNumber number,
										GrammaticalGender gender,
										GrammaticalPerson person, VerbForm form, VerbActivePassive activePassive) {
		return ConjugationRow.builder()
				.person(person)
				.number(number)
				.gender(gender)
				.form(form)
				.activePassive(activePassive)
				.pronoun(HebrewPronouns.None)
				.build();
	}
}
