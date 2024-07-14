package net.bit.reviewer.commands.base;

import net.bit.reviewer.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ICommand {
    void call(TelegramBot bot, Message value);
}
