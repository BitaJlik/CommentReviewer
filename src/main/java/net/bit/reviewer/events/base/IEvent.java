package net.bit.reviewer.events.base;

import net.bit.reviewer.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface IEvent {
    void call(TelegramBot bot, Update update);
}
