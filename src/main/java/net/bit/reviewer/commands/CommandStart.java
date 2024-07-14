package net.bit.reviewer.commands;

import net.bit.reviewer.Main;
import net.bit.reviewer.TelegramBot;
import net.bit.reviewer.commands.base.CommandBase;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonRequestUser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CommandStart extends CommandBase {

    public CommandStart() {
        super("/start");
    }

    @Override
    public void call(TelegramBot bot, Message message) {
        if (message.hasText()) {
            String textFromUser = message.getText();

            Long userId = message.getChatId();
            KeyboardRow row = new KeyboardRow();
            row.add(KeyboardButton.builder().text("Open").build());
            row.add(KeyboardButton.builder().requestUser(KeyboardButtonRequestUser.builder().build()).text("AS").build());
            ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder().keyboardRow(row).build();

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(userId.toString())
                    .text("Hello, I've received your text: " + textFromUser)
                    .replyMarkup(replyKeyboardMarkup)
                    .build();
            try {
                bot.sendMethod(sendMessage);
            } catch (TelegramApiException e) {
                Main.logger.error(e.getMessage());
            }
        } else {
            Main.logger.warning("Message is null");
        }
    }

}
