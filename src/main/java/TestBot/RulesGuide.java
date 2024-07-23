package TestBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RulesGuide {
    public static SendMessage DB(SendMessage message, String tName){

        java.sql.Connection con = Connect.connectToDB();

        StringBuilder text = new StringBuilder();

        //message.setText(Connect.readTable0(con, tName + "Head", 1));

        switch (tName){
            case "pluralNouns" -> {
                text.append((Connect.readTable(con, tName + "Head", 1)));
                text.append(Connect.readTable(con, tName, 4));
                text.append("~~~~~~~~~ ИСКЛЮЧЕНИЯ ~~~~~~~~~\n\n");
                text.append(Connect.readTable(con, tName + "Exc", 2));
            }
            case "irregularVerbs" -> {
                text.append((Connect.readTable(con, tName + "Head", 3)));
                text.append(Connect.readTable(con, tName, 1));
            }
            case "Image" -> System.out.print("Puk :  \uD83E\uDD86\uD83D\uDCA8");
            default -> {
                text.append((Connect.readTable(con, tName + "Head", 1)));
                text.append(Connect.readTable(con, tName, 4));
                text.append("~~~~~~~~~ ИСКЛЮЧЕНИЯ ~~~~~~~~~\n\n");
                text.append(Connect.readTable(con, tName + "Exs", 2));
            }
        }

        message.setText(text.toString());

//        switch (tName){
//            case "pluralNouns" -> message.setText(Connect.readTable(con, tName, "Exc"));
//            case "Image" -> System.out.print("Puk :  \uD83E\uDD86\uD83D\uDCA8");
//            case "irregularVerbs" -> message.setText(Connect.readTable1(con, tName));
//            default -> message.setText(Connect.readTable(con, tName, "Exs"));
//            // -> System.out.print("Puk :  \uD83E\uDD86\uD83D\uDCA8");
//        }
        return message;
    }
}
