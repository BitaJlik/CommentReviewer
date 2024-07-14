package net.bit.reviewer.commands.base;

import net.bit.reviewer.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class CommandBase implements ICommand {
    private final String command;

    public CommandBase(String commandName) {
        this.command = commandName;
    }

    public String getLocale(Message message) {
        return message.getFrom().getLanguageCode();
    }

    @Override
    public abstract void call(TelegramBot bot, Message value);

    public String getCommandName() {
        return command;
    }
}
