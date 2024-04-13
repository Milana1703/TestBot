package TestBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Расширение класса <code>TelegramLongPollingBot</code>
 * Реализует логику простого чат-бота для <see>TelegramBotsApi</see>
 */

public class DialogBot extends TelegramLongPollingBot {
    public static final String botName = System.getenv("botName");
    public static final String botToken = System.getenv("botToken");

    @Override
    public String getBotUsername() {
        // геттер имени бота
        return botName;
    }
    @Override
    public String getBotToken() {
        // геттер токена бота
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Проверяем появление нового сообщения в чате, и если это текст
        if (update.hasMessage() && update.getMessage().hasText()) {

            Message received_message = update.getMessage();            // Создаем переменную равную тексту присланного сообщения
            String chat_id = update.getMessage().getChatId().toString();    // Создаем переменную равную ид чата присланного сообщения
            SendMessage message = new SendMessage(); // Создаем обект-сообщение
            message.setChatId(chat_id);              // Передаем чат ид

            // Проверяем появление нового сообщения в чате, и если это текст
            if (received_message.isCommand()) {
                switch (received_message.getText()) {
                    case "/help" -> message.setText(DialogCommandResponses.HelpCommand());
                    case "/start" -> message.setText(DialogCommandResponses.StartCommand());
                    case "/anecdote" -> message.setText(DialogCommandResponses.AnecdoteCommand());
                }
            } else {
                message.setText(received_message.getText());
            }

            try {  // Отправляем обект-сообщение пользователю
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}