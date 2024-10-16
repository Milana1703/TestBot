package TestBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;


/**
 * Расширение класса <code>TelegramLongPollingBot</code>
 * Реализует логику простого чат-бота для <see>TelegramBotsApi</see>
 */

public class DialogBot extends TelegramLongPollingBot {
    public final String botName = System.getenv("botName");
    public final String botToken = System.getenv("botToken");
    private final YandexTranslate translator;
    private final HashMap<String, String> idTranslateMode;
    private final Buttons buttons;
    private final RulesGuide rulesGuide;


    public DialogBot(YandexTranslate translator, HashMap<String, String> idTranslateMode, Buttons buttons, RulesGuide rulesGuide) {
        this.translator = translator;
        this.idTranslateMode = idTranslateMode;
        this.buttons = buttons;
        this.rulesGuide = rulesGuide;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        CallbackQuery callback = update.getCallbackQuery();


        System.out.print("hasCallbackQuery: " + update.hasCallbackQuery() + "\n");
        System.out.print(callback + "\n");

        SendMessage message = new SendMessage();   // Создаем обект-сообщение

        // кнопотьки
        buttons.buttonsReplyKeyboard(message);

        if (update.hasCallbackQuery()){

            String callback_id = callback.getMessage().getChatId().toString();
            String callback_data = callback.getData();

            //SendMessage message = new SendMessage();   // Создаем обект-сообщение
            message.setChatId(callback_id);            // Передаем чат id пользователя

            System.out.print("callback_id: " + callback_id + "\n");

            try {
                execute(rulesGuide.DB(message, callback_data));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            // Проверяем появление нового сообщения в чате, и если это текст

            Message received_message = update.getMessage();
            String message_text = received_message.getText();     // Создаем переменную равную тексту присланного сообщения
            String chat_id = received_message.getChatId().toString();     // Создаем переменную равную id чата присланного сообщения

            //SendMessage message = new SendMessage();    // Создаем обект-сообщение
            message.setChatId(chat_id);                 // Передаем чат id пользователя

            // Проверяем появление нового сообщения в чате, и если это /command
            if (received_message.isCommand()) {
                switch (message_text) {
                    case "/help" -> message.setText(
                                   """
                                    Команды взаимодействия со мной :\s
                                    \s
                                    /start – вывод стартового сообщение\s
                                    /help – демонстрирация списка доступных для взаимодействия с ботом команд\s
                                    /translate – включение режима простого переводчика\s
                                    /language – смена языка перевода (RU-EN)\s
                                    /stop – выход из режима переводчика\s
                                    /anecdote – шутение смешнявки\s
                                    \s
                                    И вот, чему меня пока что научили :\s
                                    \s
                                    Rules – переход в раздел с правилами грамматики английского языка\s
                                   """);
                    case "/start" -> message.setText(
                                   """
                                    Привет \uD83C\uDF1A\s
                                       Я telegram-бот, люди создали меня, чтобы я помогал им в изучении английского языка\s
                                       Я появился совсем недавно и еще немногому успел научиться.. но мои создатели каждый день стараются сделать меня лучше)\s
                                       С помощью команды /help ты можешь ознакомиться со всем доступным функционалом и узнать как со мной взаимодействовать :з\s
                                       Надеюсь, я буду полезен для тебя \uD83D\uDC49\uD83C\uDFFC\uD83D\uDC48\uD83C\uDFFC
                                   """);

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
                    case "/anecdote" -> message.setText("Работники дорожных служб клали асфальт, один дорожник упал, теперь он внедорожник ;)");
                    default -> message.setText("Я не знаю такой команды \uD83E\uDD7A");
                }
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } // если НЕ command, а text
            else {
                if (message_text.equals("Rules")) {
                    try {
                    // то отправляем пользователю соответствующую встроенную клавиатуру
                        execute(buttons.rulesInlineKeyboard(chat_id));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print("chat_id: " + chat_id + "\n");
                    System.out.print("КоллбэкКвори : " + update.getCallbackQuery() + "\n");
                } else {
                    // переводчик
                    if (idTranslateMode.containsKey(chat_id)) {
                        message.setText(translator.translate(idTranslateMode.get(chat_id), received_message.getText()));
                    } else {
                        message.setText(received_message.getText());   // эхо-бот
                    }
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}