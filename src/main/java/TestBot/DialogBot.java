package TestBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Расширение класса <code>TelegramLongPollingBot</code>
 * Реализует логику простого чат-бота для <see>TelegramBotsApi</see>
 */

public class DialogBot extends TelegramLongPollingBot {
    public static final String botName = System.getenv("botName");
    public static final String botToken = System.getenv("botToken");
    HashMap<String, String> idTranslateMode = new HashMap<>();
    dialogCommandResponses dialogCommandResponse = new dialogCommandResponses();

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
            String chat_id = update.getMessage().getChatId().toString();    // Создаем переменную равную id чата присланного сообщения
            SendMessage message = new SendMessage(); // Создаем обект-сообщение
            message.setChatId(chat_id);              // Передаем чат id пользователя

            // HashMap<String, String> idTranslateMode = new HashMap<>();
            // String language = "ru";

            // Проверяем появление нового сообщения в чате, и если это текст
            if (received_message.isCommand()) {
                switch (received_message.getText()) {
                    case "/help" -> message.setText(dialogCommandResponse.HelpCommand());
                    case "/start" -> message.setText(dialogCommandResponse.StartCommand());
                    case "/translate" -> {
                        idTranslateMode.put(chat_id, "ru");
                        message.setText("Включен режим переводчика EN-RU");
                    }
                    case "/language" -> {
                        if (idTranslateMode.get(chat_id).equals("ru")){
                            idTranslateMode.put(chat_id, "en");
                            message.setText("Язык перевода изменен: RU-EN");
                        } else {
                            idTranslateMode.put(chat_id, "ru");
                            message.setText("Язык перевода изменен: EN-RU");
                        }
                    }
                    case "/stop" -> {
                        idTranslateMode.remove(chat_id);
                        message.setText("Вы вышли из режима переводчика");
                    }
                    case "/anecdote" -> message.setText(dialogCommandResponse.AnecdoteCommand());
                    default -> message.setText("Я не знаю такой команды \uD83E\uDD7A");
                }
            } else {
                if (idTranslateMode.containsKey(chat_id)) {
                    message.setText(dialogCommandResponse.TranslateCommand(idTranslateMode.get(chat_id), received_message.getText()));
                } else {
                    message.setText(received_message.getText());
                }
            }


            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true);  // подгоняем размер кнопочек
            replyKeyboardMarkup.setOneTimeKeyboard(true);  // оставляем кнопочки после их нажатия

            // список с рядами кнопочек
            ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();

            // ряд кнопочек
            KeyboardRow keyboardRow1 = new KeyboardRow();
            keyboardRows.add(keyboardRow1);
            keyboardRow1.add(new KeyboardButton("/start"));
            keyboardRow1.add(new KeyboardButton("/help"));

            KeyboardRow keyboardRow2 = new KeyboardRow();
            keyboardRows.add(keyboardRow2);
            keyboardRow2.add(new KeyboardButton("/translate"));
            keyboardRow2.add(new KeyboardButton("/language"));
            keyboardRow2.add(new KeyboardButton("/stop"));

            KeyboardRow keyboardRow3 = new KeyboardRow();
            keyboardRows.add(keyboardRow3);
            keyboardRow3.add(new KeyboardButton("/anecdote"));

            //добавляем лист с одним рядом кнопок в главный объект
            replyKeyboardMarkup.setKeyboard(keyboardRows);
            message.setReplyMarkup(replyKeyboardMarkup);

            try {  // Отправляем объект-сообщение пользователю
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}