package org.hebrbot.bot.actions;

import org.hebrbot.bot.model.HebrewPronouns;
import org.hebrbot.bot.model.VerbForm;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConjugateTest {

    @Test
    public void get1() {
        final String s = Conjugate.builder().pronoun(HebrewPronouns.Hu).form(VerbForm.Past).word("ללכת").build().get();
        System.out.println(s);
    }
}