package org.hebrbot.bot.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HebrewPreposition implements HebrewWord {
    String name;
    String nameNekudot;
    String root;
    String meaning;
    GrammaticalNumber number;
    GrammaticalGender gender;
    GrammaticalPerson person;
    PartOfSpeech partOfSpeech;

    @Override
    public PartOfSpeech getPartOfSpeech() {
        return PartOfSpeech.PREPOSITION;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public enum PrepositionsChars {
        BET("ב", HebrewPreposition.builder().name("ב").build()),
        MEM("מ", HebrewPreposition.builder().name("מ").build()),
        LAMED("ל", HebrewPreposition.builder().name("ל").build()),
        KAF("כ", HebrewPreposition.builder().name("כ").build());

        String he;
        HebrewPreposition preposition;
    }
}


