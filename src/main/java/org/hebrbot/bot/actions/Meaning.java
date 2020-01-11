package org.hebrbot.bot.actions;

import org.hebrbot.bot.scrapers.MilogScraper;

public class Meaning {

    //TODO: add wiki

    public static String get(String word) {
        MilogScraper m = new MilogScraper();
        return m.search(word);
    }
}
