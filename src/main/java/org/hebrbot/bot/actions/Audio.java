package org.hebrbot.bot.actions;

import org.hebrbot.bot.scrapers.ForvoScraper;

public class Audio {

    public static String get(String word) {
        ForvoScraper f = new ForvoScraper();
        String url = f.search(word);
        return url;
    }
}
