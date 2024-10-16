package TestBot;

import org.checkerframework.checker.units.qual.C;

import java.sql.*;

public class Connect {
    // Statement — базовый класс, предназначеный для выполнения простых SQL-запросов без параметров
    private PreparedStatement statement;
    private final Connection connection;
    public static final String QUERY = """
                            SELECT description
                            FROM enRules
                            WHERE name = ?;
                            """;
    public Connect() {
        this.connection = connectToDB();
        try {
            this.statement = connection.prepareStatement(QUERY);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

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

    public String readTable(String tName) {
        String text = "";

        try {
            statement.setString(1, tName);

            // ResultSet обеспечивает построчный доступ к результатам запросов
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                text = rs.getString("description");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return text;
    }
}
