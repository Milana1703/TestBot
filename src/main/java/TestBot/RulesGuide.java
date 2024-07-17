package TestBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RulesGuide {
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
