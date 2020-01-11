package org.hebrbot.bot.scrapers;

import org.junit.Test;

public class MorfixScraperTest {

    @Test
    public void search1() {
        MorfixScraper m = new MorfixScraper();
        String s = m.search("enjoy");
        System.out.println(s);
//        assertEquals(s, "נֶהֱנָה");
    }

    @Test
    public void search2() {
        MorfixScraper m = new MorfixScraper();
        String s = m.search("ללכת");
        System.out.println(s);
    }
}