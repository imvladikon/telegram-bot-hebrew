package org.hebrbot.bot.scrapers;

import org.junit.Test;

import static org.junit.Assert.*;

public class MilogScraperTest {

    @Test
    public void search() {
    }

    @Test
    public void audio() {
        MilogScraper m = new MilogScraper();
        m.audio("לְהִתְאַבֵּד");
    }
}