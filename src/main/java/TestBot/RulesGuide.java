package TestBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RulesGuide {
    private final Connect connect;

    public RulesGuide(Connect connect) {
        this.connect = connect;
    }

    public SendMessage DB(SendMessage message, String tName){
        java.sql.Connection con = connect.connectToDB();
        String text = connect.readTable(con, tName);
        message.setText(text);
        return message;
    }
}
