package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PartOfSpeech {
    VERB("פועל"),
    NOUN("עצם"),
    ADJECTIVE("תואר"),
    PREPOSITION("מילת יחס"),
    PRONOUN("מילת גוף"),
    ADVERB("תואר הפועל"),
    CONJUNCTION("מילת חיבור"),
    PARTICLE("מילית"),
    QUESTION_WORD("מילת שאלה"),
    NUMBER("מיספר"),
    MODAL("פועל עזר"),
    NONE("אין");

    String he;
}
