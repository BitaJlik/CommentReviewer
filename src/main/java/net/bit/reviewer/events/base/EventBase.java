package net.bit.reviewer.events.base;

import net.bit.reviewer.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class EventBase implements IEvent {

    public EventBase() {
    }

    public String getLocale(Update update) {
        return update.getMessage().getFrom().getLanguageCode();
    }

    @Override
    public abstract void call(TelegramBot bot, Update value);

}
