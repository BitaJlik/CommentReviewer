package net.bit.reviewer.events.base;

import net.bit.reviewer.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class EventHandler {
    private final TelegramBot bot;
    private final HashMap<String, IEvent> events = new HashMap<>();
    private IEvent defaultCallback;

    public EventHandler(TelegramBot bot) {
        this.bot = bot;
        defaultCallback = (b, message) -> {
        };
    }

    public void handleUpdate(String command, Update message) {
        IEvent messageICommand = events.get(command);
        if (messageICommand != null) {
            messageICommand.call(bot, message);
        } else {
            defaultCallback.call(bot, message);
        }
    }

    public void addHandler(String command, IEvent handler) {
        events.put(command, handler);
    }

    public void addHandler(EventBase command) {
    }

    public void removeCommand(String command) {
        events.remove(command);
    }

    public void setDefaultMessage(IEvent defaultCallback) {
        this.defaultCallback = defaultCallback;
    }
}
