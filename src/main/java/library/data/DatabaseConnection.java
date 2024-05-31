package library.data;
import java.sql.*;
public class DatabaseConnection {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = System.getenv().getOrDefault("DB_USER","root");
//    System.out.println(USER);
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD","Kishlay123@");
//    System.out.println(PASSWORD);

    // JDBC variables for opening, closing, and managing connection
    public static Connection connection;
    public static Statement statement;

    // Method to establish a connection to the database
    public static void connect() {
        try {
            System.out.println("DB_USER: " + USER);
            System.out.println("DB_PASSWORD: " + PASSWORD);

            // Check if environment variables are set
            if (USER == null || PASSWORD == null) {
                System.err.println("Error: DB_USER and/or DB_PASSWORD environment variables are not set.");
                return;
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close the connection
    public static void close() {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
            System.out.println("Disconnected from database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
