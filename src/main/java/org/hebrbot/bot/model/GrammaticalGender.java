package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum GrammaticalGender {
    Masculine("זכר"),
    Feminine("נקבה"),
    None("אף אחד"),
    Both("שניהם");

    String he;
}
