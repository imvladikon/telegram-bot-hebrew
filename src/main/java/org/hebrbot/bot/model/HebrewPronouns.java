package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum HebrewPronouns implements HebrewWord {
    Ani("אני", "אני", GrammaticalNumber.SINGULAR, GrammaticalGender.None, GrammaticalPerson.First),
    Ata("אתה", "אתה", GrammaticalNumber.SINGULAR, GrammaticalGender.Masculine, GrammaticalPerson.Second),
    At("את", "את", GrammaticalNumber.SINGULAR, GrammaticalGender.Feminine, GrammaticalPerson.Second),
    AtaAt("אתה/את", "אתה/את", GrammaticalNumber.SINGULAR, GrammaticalGender.None, GrammaticalPerson.Second),
    Hu("הוא", "הוא", GrammaticalNumber.SINGULAR, GrammaticalGender.Masculine, GrammaticalPerson.Third),
    He("היא", "היא", GrammaticalNumber.SINGULAR, GrammaticalGender.Feminine, GrammaticalPerson.Third),
    HuHe("הוא/היא", "הוא/היא", GrammaticalNumber.SINGULAR, GrammaticalGender.None, GrammaticalPerson.Third),
    Anahnu("אנחנו", "אנחנו", GrammaticalNumber.PLURAL, GrammaticalGender.None, GrammaticalPerson.First),
    Atem("אתם", "אתם", GrammaticalNumber.PLURAL, GrammaticalGender.Masculine, GrammaticalPerson.Second),
    Aten("אתן", "אתן", GrammaticalNumber.PLURAL, GrammaticalGender.Feminine, GrammaticalPerson.Second),
    AtemAten("אתם/אתן", "אתם/אתן", GrammaticalNumber.PLURAL, GrammaticalGender.None, GrammaticalPerson.Second),
    Hem("הם", "הם", GrammaticalNumber.PLURAL, GrammaticalGender.Masculine, GrammaticalPerson.Third),
    Hen("הן", "הן", GrammaticalNumber.PLURAL, GrammaticalGender.Feminine, GrammaticalPerson.Third),
    HemHen("הם/הן", "הם/הן", GrammaticalNumber.PLURAL, GrammaticalGender.None, GrammaticalPerson.Third),
    None("","", GrammaticalNumber.NONE, GrammaticalGender.None, GrammaticalPerson.None);

    HebrewPronouns(String name, String nameNekudot, GrammaticalNumber number,
                   GrammaticalGender gender, GrammaticalPerson person) {
        this.name = name;
        this.nameNekudot = nameNekudot;
        this.gender = gender;
        this.number = number;
        this.person = person;
    }

    String name;
    String nameNekudot;
    String root;
    String meaning;
    GrammaticalNumber number;
    GrammaticalGender gender;
    GrammaticalPerson person;
    PartOfSpeech partOfSpeech;

    public PartOfSpeech getPartOfSpeech() {
        return PartOfSpeech.PRONOUN;
    }
}
