package TestBot;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection connectToDB() {

        final String dbName = System.getenv("dbName");
        final String dbUser = System.getenv("dbUser");
        final String dbPass = System.getenv("dbPass");
        final String dbUrl = System.getenv("dbUrl");

        Connection connect_object = null;

        try {
            connect_object = DriverManager.getConnection(dbUrl+dbName, dbUser, dbPass);
            if(connect_object != null){
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return connect_object;
    }

    public static String readTable(Connection connection, String tName, Boolean exc) {
    // Statement — базовый класс, предназначеный для выполнения простых SQL-запросов без параметров
        Statement statement;
        ResultSet rs;    // ResultSet обеспечивает построчный доступ к результатам запросов

        StringBuilder ruleText = new StringBuilder("Правило :\n\n");

        try {
            statement = connection.createStatement();   // создаем Statement объект для отправки инструкций SQL в БД
            String query = "SELECT CAST(columnOne AS text), CAST(columnTwo AS text), CAST(columnThree AS text), "
                    + "CAST(columnFour AS text) FROM " + tName + "Exc" + ";";
            rs = statement.executeQuery(query);

            while (rs.next()) {
                ruleText.append(rs.getString("columnOne")).append("\n");
                ruleText.append(rs.getString("columnTwo")).append("\n");
                ruleText.append(rs.getString("columnThree")).append("\n");
                ruleText.append(rs.getString("columnFour")).append("\n").append("\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuilder excText = new StringBuilder();

        if (exc){

            excText = new StringBuilder("\nИсключения :\n\n");

            try {
                statement = connection.createStatement();
                String query = "SELECT CAST(columnOne AS text), CAST(columnTwo AS text), CAST(columnThree AS text), "
                        + "CAST(columnFour AS text) FROM " + tName + "Exc" + ";";
                rs = statement.executeQuery(query);

                while (rs.next()) {
                    excText.append(rs.getString("columnOne")).append("\n");
                    excText.append(rs.getString("columnTwo")).append("\n");
                    excText.append(rs.getString("columnThree")).append("\n");
                    excText.append(rs.getString("columnFour")).append("\n").append("\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return ruleText + excText.toString();
    }
}
