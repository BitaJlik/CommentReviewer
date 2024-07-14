package net.bit.reviewer;

import net.bit.reviewer.commands.CommandStart;
import net.bit.reviewer.commands.base.CommandHandler;
import net.bit.reviewer.events.base.EventHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public class TelegramBot extends TelegramLongPollingBot {
    private final CommandHandler commandHandler = new CommandHandler(this);
    private final EventHandler eventHandler = new EventHandler(this);

    public TelegramBot() {
        super(PrivateAssets.BOT_TOKEN);
        commandHandler.addHandler(new CommandStart());
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = message.getFrom();
        String messageText = message.getText();
        if (messageText.startsWith("/")) {
            commandHandler.handleCommand(messageText, message);
        } else {
            eventHandler.handleUpdate("", update);
        }
    }

    public <T extends Serializable, Method extends BotApiMethod<T>> T sendMethod(Method method) throws TelegramApiException {
        return sendApiMethod(method);
    }

    @Override
    public String getBotUsername() {
        return PrivateAssets.BOT_NAME;
    }
}
