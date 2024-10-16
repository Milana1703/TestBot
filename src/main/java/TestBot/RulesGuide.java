package TestBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RulesGuide {
    private final Connect connect;

    public RulesGuide(Connect connect) {
        this.connect = connect;
    }

    public SendMessage DB(SendMessage message, String tName){

        java.sql.Connection con = connect.connectToDB();

        StringBuilder text = new StringBuilder();

        switch (tName){
            case "pluralNouns" -> {
                text.append((connect.readTable(con, tName + "Head", 1)));
                text.append(connect.readTable(con, tName, 4));
                text.append("~~~~~~~~~ ИСКЛЮЧЕНИЯ ~~~~~~~~~\n\n");
                text.append(connect.readTable(con, tName + "Exc", 2));
            }
            case "irregularVerbs" -> {
                text.append((connect.readTable(con, tName + "Head", 3)));
                text.append(connect.readTable(con, tName, 1));
            }
            case "Image" -> System.out.print("Puk :  \uD83E\uDD86\uD83D\uDCA8");
            default -> {
                text.append((connect.readTable(con, tName + "Head", 1)));
                text.append(connect.readTable(con, tName, 4));
                text.append("~~~~~~~~~ ИСКЛЮЧЕНИЯ ~~~~~~~~~\n\n");
                text.append(connect.readTable(con, tName + "Exs", 2));
            }
        }
        message.setText(text.toString());
        return message;
    }
}
