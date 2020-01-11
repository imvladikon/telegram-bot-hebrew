package org.hebrbot.bot.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

	@Test
	public void removeAll1() {
		assertEquals("תיגמל", StringUtils.removeAll(" ~ תיגמל", "*", "!", "~"));
	}

	@Test
	public void removeNiqqud1() {
	    assertEquals("מתגמל",  StringUtils.removeNiqqud("מְתַגְמֵל"));
	}

	@Test
	public void isHebrew1() {
		assertTrue(StringUtils.isHebrew("ללכת"));
	}

	@Test
	public void isHebrew2() {
		assertFalse(StringUtils.isHebrew("word"));
	}
}