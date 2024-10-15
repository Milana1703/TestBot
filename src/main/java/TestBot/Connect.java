package TestBot;

import java.sql.*;

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

    public static String readTable(Connection connection, String tName, Integer columnNumber) {
        // Statement — базовый класс, предназначеный для выполнения простых SQL-запросов без параметров
        PreparedStatement statement;
        ResultSet rs;    // ResultSet обеспечивает построчный доступ к результатам запросов

        StringBuilder text = new StringBuilder();

        switch (columnNumber) {
            case 2 -> {
                try {
                    String query = String.format("""
                            SELECT CAST(columnOne AS text),
                                   CAST(columnTwo AS text)
                            FROM %s;
                            """, tName);
                    statement = connection.prepareStatement(query);
                    rs = statement.executeQuery();

                    while (rs.next()) {
                        text.append(rs.getString("columnOne")).append("\n");
                        text.append(rs.getString("columnTwo")).append("\n").append("\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> {
                try {
                    String query = String.format("""
                            SELECT CAST(columnOne AS text),
                                   CAST(columnTwo AS text),
                                   CAST(columnThree AS text)
                            FROM %s;
                            """, tName);
                    statement = connection.prepareStatement(query);
                    rs = statement.executeQuery();

                    while (rs.next()) {
                        text.append(rs.getString("columnOne")).append("\n");
                        text.append(rs.getString("columnTwo")).append("\n");
                        text.append(rs.getString("columnThree")).append("\n").append("\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 4 -> {
                try {
                    String query = String.format("""
                            SELECT CAST(columnOne AS text),
                                   CAST(columnTwo AS text),
                                   CAST(columnThree AS text),
                                   CAST(columnFour AS text)
                            FROM %s;
                            """, tName);
                    statement = connection.prepareStatement(query);
                    rs = statement.executeQuery();

                    while (rs.next()) {
                        text.append(rs.getString("columnOne")).append("\n");
                        text.append(rs.getString("columnTwo")).append("\n");
                        text.append(rs.getString("columnThree")).append("\n");
                        text.append(rs.getString("columnFour")).append("\n").append("\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            default -> {
                try {
                    String query = String.format("""
                            SELECT CAST(columnOne AS text)
                            FROM %s;
                            """, tName);
                    statement = connection.prepareStatement(query);
                    rs = statement.executeQuery();

                    while (rs.next()) {
                        text.append(rs.getString("columnOne")).append("\n").append("\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return text.toString();
    }
}
