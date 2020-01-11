package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum VerbForm {
						Past("עבר", asList("שלשום", "לפני", "אתמול")),
						Present("הווה", asList("היום", "עכשיו")),
						Future("עתיד", asList("מחרתיים", "מחר", "הבאה")),
						Imperative("ציווי"),
						Infinitive("מקור"),
						PresentPassive("הווה סביל (פעול)"),
						None("אין");

	VerbForm(String he) {
		this.he = he;
	}

	String he;
	List<String> wordsPointers = new ArrayList<>();
}
