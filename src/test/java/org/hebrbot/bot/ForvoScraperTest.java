package org.hebrbot.bot;

import org.hebrbot.bot.scrapers.ForvoScraper;
import org.junit.Test;

public class ForvoScraperTest {

    @Test
    public void search1() {
        ForvoScraper f = new ForvoScraper();
        f.search("ללכת");
        //https://audio00.forvo.com/audios/mp3/g/c/gc_9072448_58_1106691_1.mp3
    }

    @Test
    public void search2() {
        ForvoScraper f = new ForvoScraper();
        f.search("הסתברות");
    }

    @Test
    public void search3() {
        ForvoScraper f = new ForvoScraper();
        f.search("מחר");
    }
}