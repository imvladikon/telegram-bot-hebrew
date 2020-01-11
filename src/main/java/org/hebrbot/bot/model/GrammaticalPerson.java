package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum GrammaticalPerson {
    First("גוף ראשון"),
    Second("גוף שני"),
    Third("גוף שלישי"),
    None("אין"),
    All("כל האפשרויות");

    String he;
}
