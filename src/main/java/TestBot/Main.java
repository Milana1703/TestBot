package TestBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

    //  переменные окружения :
    //  run -> edit configuration -> application -> telegram bot

public class Main{

    public static void main(String[] args) {

//        System.out.println(DialogBot.botName);
//        System.out.println(DialogBot.botToken);

        TelegramBotsApi telegramBotsApi = null;
        try {  // пробуем зарегестрировать бота
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new DialogBot()); // botName, botToken
            System.out.println("Bot started! \uD83D\uDD25");
        } catch (TelegramApiException exc)        {
            throw new RuntimeException(exc);
        }
    }
}
