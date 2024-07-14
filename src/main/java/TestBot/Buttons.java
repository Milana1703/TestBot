package TestBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;

public class Buttons {
    public static void buttonsReplyKeyboard(SendMessage message){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);  // подгоняем размер кнопочек
        replyKeyboardMarkup.setOneTimeKeyboard(true);  // оставляем кнопочки после их нажатия
        replyKeyboardMarkup.setInputFieldPlaceholder("puk");  // просто мем какой-то, в поле ввода

        // список с рядами кнопочек
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRows.add(keyboardRow1);
        keyboardRow1.add(new KeyboardButton("Rules"));

        // ряд кнопочек
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRows.add(keyboardRow2);
        keyboardRow2.add(new KeyboardButton("/start"));
        keyboardRow2.add(new KeyboardButton("/help"));

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRows.add(keyboardRow3);
        keyboardRow3.add(new KeyboardButton("/translate"));
        keyboardRow3.add(new KeyboardButton("/language"));
        keyboardRow3.add(new KeyboardButton("/stop"));

        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRows.add(keyboardRow4);
        keyboardRow4.add(new KeyboardButton("/anecdote"));

        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(replyKeyboardMarkup);
    }
}
