package org.hebrbot.bot.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;

public enum UserMessageType {
    Command,
    Text,
    Reply,
    None;

    public static UserMessageType from(Message message) {
        String text = message.getText();
        if(text.startsWith("/") || message.isCommand()) {
            return UserMessageType.Command;
        }
        if(message.isReply()) {
            return UserMessageType.Reply;
        }
        if (message.isUserMessage()) {
            return UserMessageType.Text;
        }
        return UserMessageType.None;
    }
}
