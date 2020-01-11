package org.hebrbot.bot.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class LanguageDetectorService {

	@SneakyThrows
	public String detect(String text) {
//		List<LanguageProfile> languageProfiles = new LanguageProfileReader()
//				.readBuiltIn(asList("es", "he", "pt", "en", "ru", "uk").stream()
//						.map(s -> LdLocale.fromString(s))
//						.collect(Collectors.toList()));
//		//        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();
//		LanguageDetector languageDetector =
//				LanguageDetectorBuilder.create(NgramExtractors.standard())
//						.withProfiles(languageProfiles)
//						.build();
//		TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
//		TextObject textObject = textObjectFactory.forText("my text");
//		return languageDetector.detect(textObject).transform(l -> l.getLanguage()).or("");
		return "";
	}
}
