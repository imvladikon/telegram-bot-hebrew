package org.hebrbot.bot.service;

import org.hebrbot.bot.actions.Translate;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageDetectorServiceTest {

    @Test
    public void detectLang1() {
//        LanguageDetectorService ls = new LanguageDetectorService();
//        System.out.println(ls.detect("saudade"));
    }

    @Test
    public void detectLang2() {
        Translate t = new Translate();
        final String s1 = t.get("saudade");
        assertEquals("pt",t.getResponse().getSrc());
    }
}