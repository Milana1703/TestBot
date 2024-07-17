package TestBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RulesGuide {
    public static SendMessage rulesInlineKeyboard (String chat_id) {

        SendMessage message = new SendMessage();   // создаем объект сообщения
        message.setChatId(chat_id);
        message.setText("Для просмотра необходимого правила тыкни на соответствующий раздел");

        // создание объекта встроенной клавиатуры
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();   // список списков кнопок, который впоследствии объединит их ряды
        List<InlineKeyboardButton> inlineRow1 = new ArrayList<>();    // список кнопок первого ряда
        InlineKeyboardButton inlineButton1 = new InlineKeyboardButton();   // первая кнопку в ряду
        inlineButton1.setText("Образование множественного числа существительных");    // текст на кнопке
        inlineButton1.setCallbackData("pluralNouns");   // устанавливаем параметр callback_data

        List<InlineKeyboardButton> inlineRow2 = new ArrayList<>();

        InlineKeyboardButton inlineButton2 = new InlineKeyboardButton();
        inlineButton2.setText("Правило2");
        inlineButton2.setCallbackData("Rule2");

        InlineKeyboardButton inlineButton3 = new InlineKeyboardButton();
        inlineButton3.setText("Правило3");
        inlineButton3.setCallbackData("Rule3");

        List<InlineKeyboardButton> inlineRow3 = new ArrayList<>();

        InlineKeyboardButton inlineButton4 = new InlineKeyboardButton();
        inlineButton4.setText("Правило4");
        inlineButton4.setCallbackData("Rule4");

        // добавляем кнопки в первый ряд в том порядке, какой нам необходим.

        inlineRow1.add(inlineButton1);
        inlineRow2.add(inlineButton2);

        inlineRow2.add(inlineButton3);
        inlineRow3.add(inlineButton4);


        // адрес Интернет страницы устанавливается через setUrl(String s)

        List<InlineKeyboardButton> inlineRow4 = new ArrayList<>();
        InlineKeyboardButton inlineButtonImage = new InlineKeyboardButton();

        inlineButtonImage.setText("Остальные правила");
        // устанавливаем url, указывая строковый параметр с адресом страницы
        inlineButtonImage.setUrl("https://avatars.mds.yandex.net/i?id=880f7d3eda3b1cec1d6afe485af65cd7ea02b442-5233241-images-thumbs&n=13");

        inlineButtonImage.setCallbackData("Image");
        inlineRow4.add(inlineButtonImage);

        // настраиваем разметку всей клавиатуры

        rowsInline.add(inlineRow1);
        rowsInline.add(inlineRow2);
        rowsInline.add(inlineRow3);
        rowsInline.add(inlineRow4);
        // ...
        // rowsInline.add(rowInline11);

        // добавляем встроенную клавиатуру в сообщение
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

         return message;
    }
    public static SendMessage DB(SendMessage message, String callback_data){

        java.sql.Connection con = Connect.connectToDB();

        if (callback_data.equals("pluralNouns")){
            message.setText(Connect.readTable(con, "pluralNouns", true));
        } else {
            System.out.print("Puk :  \uD83E\uDD86\uD83D\uDCA8");
        }
        return message;
    }
}
