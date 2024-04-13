package TestBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main{
    // переменные окружения :
    // run -> edit configuration -> application -> telegram bot

    // public static final String botName = System.getenv("botName");
    // public static final String botToken = System.getenv("botToken");

    public static void main(String[] args) {
        System.out.println(DialogBot.botName);
        System.out.println(DialogBot.botToken);

        TelegramBotsApi telegramBotsApi = null;
        try {  // пробуем зарегестрировать бота
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new DialogBot()); // botName, botToken
            System.out.println("Bot started!");
        } catch (TelegramApiException exc)        {
            throw new RuntimeException(exc);
        }
    }
}
