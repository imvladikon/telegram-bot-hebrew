package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum GrammaticalNumber {
    SINGULAR("Singular"),
    PLURAL("Plural"),
    NONE("None"),
    BOTH("Singular|Plural");

    String name;
}
