package org.hebrbot.bot.model;

public interface HebrewWord {
    String getName();
    String getNameNekudot();
    String getRoot();
    String getMeaning();
    GrammaticalNumber getNumber();
    GrammaticalGender getGender();
    GrammaticalPerson getPerson();
    PartOfSpeech getPartOfSpeech();
}
