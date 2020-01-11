package org.hebrbot.bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordTranslation implements Translatable {
    Language language;
    String value;
    HebrewWord word;

    @Override
    public String getTranslation() {
        return this.value;
    }
}
