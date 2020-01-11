package org.hebrbot.bot;

import org.hebrbot.bot.actions.Audio;
import org.hebrbot.bot.actions.Conjugate;
import org.hebrbot.bot.actions.Meaning;
import org.hebrbot.bot.actions.Translate;
import org.hebrbot.bot.dao.HebrewVerbDao;
import org.hebrbot.bot.model.HebrewPronouns;
import org.hebrbot.bot.model.HebrewVerb;
import org.hebrbot.bot.model.VerbForm;
import org.hebrbot.bot.service.HebrewVerbService;
import org.hebrbot.bot.telegram.UserMessageType;
import lombok.SneakyThrows;
import org.hebrbot.bot.utils.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This example bot is an echo bot that just repeats the messages sent to him
 */
@Component
public class ExampleBot extends TelegramLongPollingBot {

	private static final Logger logger = LoggerFactory.getLogger(ExampleBot.class);

	@Value("${bot.token}")
	private String token;

	@Value("${bot.username}")
	private String username;

	@Autowired
	HebrewVerbService hebrewVerbService;

	@Autowired
	HebrewVerbDao hebrewVerbDao;

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	private static final int WAITINGCHANNEL = 1;

	private final ConcurrentHashMap<Integer, Integer> userState = new ConcurrentHashMap<>();

	@SneakyThrows
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) {
			final HebrewVerb verb = hebrewVerbDao.getByName("להיתקף");
			Hibernate.isInitialized(verb.getInflections());
			Message message = update.getMessage();
			//            message.getFrom().getLanguageCode() IETF language tag
			switch (UserMessageType.from(message)) {
			case Text: {
				handleIncomingMessage(message);
				break;
			}
			case Reply: {
				handleReplyMessage(message);
				break;
			}
			case Command: {
				handleIncomingCommand(message);
				break;
			}
			case None: {
				break;
			}
			}
			//            try {
			//                handleIncomingMessage(message);
			//                logger.info("Sent message \"{}\" to {}", message.getText(), message.getChatId());
			//            } catch (TelegramApiException e) {
			//                logger.error("Failed to send message \"{}\" to {} due to error: {}", message.getText(), message.getChatId(), e.getMessage());
			//            }
		}
	}

	private void handleReplyMessage(Message message) throws TelegramApiException {
		String word = message.getText().trim();
		final BotCommand command = BotCommand.fromReply(message.getReplyToMessage().getText());
		executeCommand(message, command, word);
	}

	private void executeCommand(Message message, BotCommand command, String word) {
		switch (command) {
		case Past: {
			sendMessageTo(message, Conjugate.get(word, VerbForm.Past));
			break;
		}
		case Present: {
			sendMessageTo(message, Conjugate.get(word, VerbForm.Present));
			break;
		}
		case Future: {
			sendMessageTo(message, Conjugate.get(word, VerbForm.Future));
			break;
		}
		case Imperative: {
			sendMessageTo(message, Conjugate.get(word, VerbForm.Imperative));
			break;
		}
		case Conjugate: {
			sendMessageTo(message, Conjugate.get(word));
			break;
		}

		case Translate: {
			sendMessageTo(message, new Translate().get(word));
			break;
		}
		case Meaning: {
			sendMessageTo(message, Meaning.get(word));
			break;
		}
		case Reverso: {
			break;
		}
		case Audio: {
			//TODO: search by 2 step, if not found, then stemm
			sendAudioTo(message, Audio.get(word), word);
			break;
		}
		case None: {
			break;
		}
		}
	}

	private void sendReplyMessageTo(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(message.getChatId());
		ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
		forceReplyKeyboard.setSelective(true);
		sendMessage.setText(text);
		sendMessage.setReplyMarkup(forceReplyKeyboard);
		sendMessage.setReplyToMessageId(message.getMessageId());
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			System.out.println(e.getMessage());
			//			sendErrorMessage(message, e.getMessage());
		}
	}

	private void handleIncomingCommand(Message message) throws TelegramApiException {
		final String[] strings = (message.getText().trim()).split("\\s+");
		if (strings.length == 0)
			return;
		final BotCommand command = BotCommand.from(strings[0].trim());
		String word = !message.getText()
				.contains(" ") ? "" : message.getText().substring(message.getText().indexOf(" "));
		if (word.isEmpty()) {
			sendReplyMessageTo(message, command.getReplyText());
			return;
		}
		executeCommand(message, command, word);
	}

	private void handleIncomingMessage(Message message) throws TelegramApiException {
		//        int state = userState.getOrDefault(message.getFrom().getId(), 0);
		//TODO:
		//1. detect lang
		// preproc - nekudot, stemming
		//2. parser (NLP?) expression
		//3. execute expression
		// detect time if it's not infinitive
        //maybe without +
		final String text = message.getText().trim();
//		if (text.contains("+")) {
			final HebrewPronouns pronoun = Arrays.stream(HebrewPronouns.values())
					.filter(p -> text.contains(p.getName()))
					.findFirst()
					.orElse(HebrewPronouns.None);
			final VerbForm form = Arrays.stream(VerbForm.values())
					.filter(p -> text.contains(p.getHe())
						|| text.toLowerCase().contains(p.name().toLowerCase())
					 || p.getWordsPointers().stream().anyMatch(text::contains))
					.findFirst()
					.orElse(VerbForm.None);
			final String word = StringUtils.removeAll(	text.replaceAll("\\+", ""),
														pronoun.getName(),
														form.name(),
														form.getHe());
			sendMessageTo(	message,
							Conjugate.builder()
									.form(form)
									.pronoun(pronoun)
									.word(word)
									.build()
									.get());
//		} else {
			//TODO: check is one verb word
//			sendMessageTo(message, Conjugate.get(text, VerbForm.None));
//		}
	}

	private void sendAudioTo(Message message, String url, String caption) {
		SendVoice sendMessage = new SendVoice();
		sendMessage.setChatId(message.getChatId());
		sendMessage.setVoice(url);
		//        sendMessage.setDuration(5);
		//        sendMessage.setAudio(url);
		sendMessage.setCaption(caption);
		//        sendMessage.setTitle(caption);
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			System.out.println(e.getMessage());
			//			sendErrorMessage(message, e.getMessage());
		}
	}

	private void sendMessageTo(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		//        sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId());

		sendMessage.setText(text);
		//        sendMessage.enableMarkdown(true);
		sendMessage.enableHtml(true);

		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			System.out.println(e.getMessage());
			//			sendErrorMessage(message, e.getMessage());
		}
	}

	private void sendHelpMessage(	Long chatId,
									Integer messageId,
									ReplyKeyboardMarkup replyKeyboardMarkup) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setReplyToMessageId(messageId);
		if (replyKeyboardMarkup != null) {
			sendMessage.setReplyMarkup(replyKeyboardMarkup);
		}
		sendMessage.setText("HELP_TEXT");
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			//			BotLogger.error(LOGTAG, e);
		}
	}

	@PostConstruct
	public void start() {
		logger.info("username: {}, token: {}", username, token);
	}

	static String commands() {
		return "translate - translate the word\n"
			+ "        conjugate - get conjugation of the verb\n"
			+ "        meaning - get the meaning of the word\n"
			+ "        past - get the past tense\n" + "        present - get the present tense\n"
			+ "        future - get the future tense\n" + "        imperative - get imperative\n"
			+ "        audio - get audio";

	}
}
