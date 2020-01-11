package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HebrewAdjective implements HebrewWord {
    String name;
    String nameNekudot;
    String root;
    String meaning;
    GrammaticalNumber number;
    GrammaticalGender gender;
    GrammaticalPerson person;
    PartOfSpeech partOfSpeech;
    AdjectivesMishkal mishkal;

    @Override
    public PartOfSpeech getPartOfSpeech() {
        return PartOfSpeech.ADJECTIVE;
    }
}
