package TestBot;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;

//  переменные окружения :
//  run -> edit configuration -> application -> telegram bot

public class Main{
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi;
        try {  // пробуем зарегестрировать бота
            Gson gson = new Gson();
            YandexTranslate yTranslate = new YandexTranslate(gson);
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new DialogBot(yTranslate, new HashMap<>()));  // botName, botToken
            System.out.println("Bot started! \uD83D\uDD25");
        } catch (TelegramApiException exc)        {
            throw new RuntimeException(exc);
        }
    }
}