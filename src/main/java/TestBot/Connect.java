package TestBot;

import java.sql.*;

public class Connect {
    public Connection connectToDB() {

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

    public String readTable(Connection connection, String tName) {
        // Statement — базовый класс, предназначеный для выполнения простых SQL-запросов без параметров
        PreparedStatement statement;
        ResultSet rs;    // ResultSet обеспечивает построчный доступ к результатам запросов

        String text = "";

        try {
            String query = """
                            SELECT description
                            FROM enRules
                            WHERE name = ?;
                            """;
            statement = connection.prepareStatement(query);
            statement.setString(1, tName);
            rs = statement.executeQuery();

            while (rs.next()) {
                text = rs.getString("description");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return text;
    }
}
