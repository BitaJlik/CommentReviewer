package net.bit.reviewer.commands.base;

import net.bit.reviewer.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;

public class CommandHandler {
    private final TelegramBot bot;
    private final HashMap<String, ICommand> commands = new HashMap<>();
    private ICommand defaultCallback;

    public CommandHandler(TelegramBot bot) {
        this.bot = bot;
        defaultCallback = (b, message) -> {
            User user = message.getFrom();
            //bot.execute(new SendMessage(String.valueOf(message.getChat().getId()), "Hello " + user.getFirstName()));
        };
    }

    public void handleCommand(String command, Message message) {
        ICommand messageICommand = commands.get(command);
        if (messageICommand != null) {
            messageICommand.call(bot, message);
        } else {
            defaultCallback.call(bot, message);
        }
    }

    public void addHandler(String command, ICommand handler) {
        commands.put(command, handler);
    }

    public void addHandler(CommandBase command) {
        commands.put(command.getCommandName(), command);
    }

    public void removeCommand(String command) {
        commands.remove(command);
    }

    public void setDefaultMessage(ICommand defaultCallback) {
        this.defaultCallback = defaultCallback;
    }
}
