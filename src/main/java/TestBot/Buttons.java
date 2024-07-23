package TestBot;

import java.util.List;
import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

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

    public static SendMessage rulesInlineKeyboard (String chat_id) {

        SendMessage message = new SendMessage();   // создаем объект сообщения
        message.setChatId(chat_id);
        message.setText("Для просмотра необходимого правила тыкни на соответствующий раздел");

        // создание объекта встроенной клавиатуры
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();   // список списков кнопок, который впоследствии объединит их ряды
        List<InlineKeyboardButton> inlineRow1 = new ArrayList<>();    // список кнопок первого ряда
        InlineKeyboardButton inlineButton1 = new InlineKeyboardButton();   // первая кнопку в ряду
        inlineButton1.setText("Множественное число существительных");    // текст на кнопке
        inlineButton1.setCallbackData("pluralNouns");   // устанавливаем параметр callback_data

        List<InlineKeyboardButton> inlineRow2 = new ArrayList<>();

        InlineKeyboardButton inlineButton2 = new InlineKeyboardButton();
        inlineButton2.setText("Past Simple");
        inlineButton2.setCallbackData("pastSimple");

        InlineKeyboardButton inlineButton3 = new InlineKeyboardButton();
        inlineButton3.setText("Present Simple");
        inlineButton3.setCallbackData("presentSimple");

        InlineKeyboardButton inlineButton4 = new InlineKeyboardButton();
        inlineButton4.setText("Future Simple");
        inlineButton4.setCallbackData("futureSimple");

        List<InlineKeyboardButton> inlineRow3 = new ArrayList<>();

        InlineKeyboardButton inlineButton5 = new InlineKeyboardButton();
        inlineButton5.setText("Past Continuous");
        inlineButton5.setCallbackData("pastContinuous");

        InlineKeyboardButton inlineButton6 = new InlineKeyboardButton();
        inlineButton6.setText("Present Continuous");
        inlineButton6.setCallbackData("presentContinuous");

        InlineKeyboardButton inlineButton7 = new InlineKeyboardButton();
        inlineButton7.setText("Future Continuous");
        inlineButton7.setCallbackData("futureContinuous");

        List<InlineKeyboardButton> inlineRow4 = new ArrayList<>();

        InlineKeyboardButton inlineButton8 = new InlineKeyboardButton();
        inlineButton8.setText("Past Perfect");
        inlineButton8.setCallbackData("pastPerfect");

        InlineKeyboardButton inlineButton9 = new InlineKeyboardButton();
        inlineButton9.setText("Present Perfect");
        inlineButton9.setCallbackData("presentPerfect");

        InlineKeyboardButton inlineButton10 = new InlineKeyboardButton();
        inlineButton10.setText("Future Perfect");
        inlineButton10.setCallbackData("futurePerfect");

        List<InlineKeyboardButton> inlineRow5 = new ArrayList<>();

        InlineKeyboardButton inlineButton11 = new InlineKeyboardButton();
        inlineButton11.setText("Неправильные глаголы");
        inlineButton11.setCallbackData("irregularVerbs");

        // добавляем кнопки в первый ряд в том порядке, какой нам необходим.

        inlineRow1.add(inlineButton1);

        inlineRow2.add(inlineButton2);
        inlineRow2.add(inlineButton3);
        inlineRow2.add(inlineButton4);

        inlineRow3.add(inlineButton5);
        inlineRow3.add(inlineButton6);
        inlineRow3.add(inlineButton7);

        inlineRow4.add(inlineButton8);
        inlineRow4.add(inlineButton9);
        inlineRow4.add(inlineButton10);

        inlineRow5.add(inlineButton11);

        // адрес Интернет страницы устанавливается через setUrl(String s)

        List<InlineKeyboardButton> inlineRow6 = new ArrayList<>();
        InlineKeyboardButton inlineButtonImage = new InlineKeyboardButton();

        inlineButtonImage.setText("Остальные правила");
        // устанавливаем url, указывая строковый параметр с адресом страницы
        inlineButtonImage.setUrl("https://avatars.mds.yandex.net/i?id=880f7d3eda3b1cec1d6afe485af65cd7ea02b442-5233241-images-thumbs&n=13");

        inlineButtonImage.setCallbackData("Image");
        inlineRow6.add(inlineButtonImage);

        // настраиваем разметку всей клавиатуры

        rowsInline.add(inlineRow1);
        rowsInline.add(inlineRow2);
        rowsInline.add(inlineRow3);
        rowsInline.add(inlineRow4);
        //rowsInline.add(inlineRow5);
        rowsInline.add(inlineRow6);

        // добавляем встроенную клавиатуру в сообщение
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }
}
