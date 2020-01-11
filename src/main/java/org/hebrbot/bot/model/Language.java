package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Language {
    HE("Hebrew"),
    EN("English"),
    RU("Russian");

    String name;
}
