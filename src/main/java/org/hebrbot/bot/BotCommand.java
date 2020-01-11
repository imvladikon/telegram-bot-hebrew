package org.hebrbot.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
public enum BotCommand {
    Meaning("meaning", "meaning:"),
    Translate("translate", "translate:"),
    Conjugate("conjugate", "conjugate for:"),
    Audio("audio", "audio for word:"),
    Reverso("reverso", "reverso:"),
    Past("past", "past for:"),
    Present("present", "present for:"),
    Future("future", "future for:"),
    Imperative("imperative", "imperative for:"),
    None("", "");

    @Getter
    String name;
    @Getter
    String replyText;

    public static BotCommand from(String text) {
        String name = !text.startsWith("/") ? text : text.substring(1);
        return Arrays.stream(BotCommand.values())
                .filter(e -> e.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(BotCommand.None);
    }

    public static BotCommand fromReply(String text) {
        return Arrays.stream(BotCommand.values())
                .filter(e -> e.replyText.equalsIgnoreCase(text))
                .findFirst()
                .orElse(BotCommand.None);
    }
}
