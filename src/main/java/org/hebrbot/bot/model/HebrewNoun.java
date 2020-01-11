package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HebrewNoun implements HebrewWord {
    String name;
    String nameNekudot;
    String root;
    String meaning;
    GrammaticalNumber number;
    GrammaticalGender gender;
    GrammaticalPerson person;
    PartOfSpeech partOfSpeech;
    NounsMishkal mishkal;

    @Override
    public PartOfSpeech getPartOfSpeech() {
        return PartOfSpeech.NOUN;
    }
}
