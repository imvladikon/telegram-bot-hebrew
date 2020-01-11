package org.hebrbot.bot.scrapers;

import org.hebrbot.bot.model.*;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PealimScraperTest {

	@Test
	public void search1() {
		PealimScraper p = new PealimScraper();
		final HebrewVerb verb = p.search("ללכת");
		assertEquals("לָלֶכֶת", verb.getNameNekudot());
		assertEquals(28, verb.getInflections().size());
		final String baseFormNameNekudot = verb.getInflections()
				.stream()
				.filter(i -> i.getForm() == VerbForm.Past && i.getPronoun() == HebrewPronouns.Hu)
				.findFirst()
				.map(HebrewVerbInflection::getNameNekudot)
				.orElse(null);
		assertEquals("הָלַךְ", baseFormNameNekudot);

	}

	@Test
	public void search2() {
		PealimScraper p = new PealimScraper();
		final HebrewVerb verb = p.search("לְתַגְמֵל");
		assertEquals("לְתַגְמֵל", verb.getNameNekudot());
		assertEquals(51, verb.getInflections().size());
		assertEquals(28, verb.getActiveInflections().size());
		assertEquals(23, verb.getPassiveInflections().size());
		final String baseFormName = verb.getActiveInflections()
				.stream()
				.filter(i -> i.getForm() == VerbForm.Past && i.getPronoun() == HebrewPronouns.Hu)
				.findFirst()
				.map(HebrewVerbInflection::getName)
				.orElse(null);
		assertEquals("תיגמל", baseFormName);

	}
}