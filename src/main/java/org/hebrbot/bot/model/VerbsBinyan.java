package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum VerbsBinyan {

	//@formatter:off
    paal("paal","пааль"),
    nifal("nifal","нифъаль"),
    piel("piel","пиэль"),
    pual("pual","пуаль"),
    hifil("hifil","hифъиль"),
    hufal("hufal","hуфъаль"),
    hitpael("hitpael","hитпаэль"),
	none;
    //@formatter:on

	String name;
	String ru;

	public static VerbsBinyan from(String name) {
		return EnumSet.allOf(VerbsBinyan.class)
				.stream()
				.filter(e -> e.name.equalsIgnoreCase(name) || e.ru.equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}

}