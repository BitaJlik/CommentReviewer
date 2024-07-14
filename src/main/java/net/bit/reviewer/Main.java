package net.bit.reviewer;

import net.bit.reviewer.localization.I18n;
import net.bit.reviewer.logger.BitLogger;
import net.bit.reviewer.logger.CompositeLogger;
import net.bit.reviewer.logger.ConsoleLogger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static BitLogger logger = new BitLogger();

    public static void main(String[] args) throws TelegramApiException {
        logger.setLogger(new CompositeLogger(new ConsoleLogger()/*, new FileLogger(System.getenv("appdata") + "/log.dat")*/));
        PrivateAssets.init();
        logger.info("Starting");
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot());

        I18n.init();

        logger.info("Bot started!");
    }

}
