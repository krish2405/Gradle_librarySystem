package library.DOAforBooks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import library.data.DatabaseConnection;
import library.utility.Utilityfunctions;

    public class BooksDataAccess {

        public static void createBooksTable(String tableName, String columnDefinitions) throws SQLException {
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columnDefinitions + ")";
            DatabaseConnection.statement.executeUpdate(query);
        }

        public static boolean tableExists(String tableName) throws SQLException {
            return Utilityfunctions.tableExists(tableName);
        }

        public static ResultSet getAllBooks() throws SQLException {
            String query = "SELECT * FROM books";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        }

        public static int insertBook(String b_name, String genres, String author, int quantity, double price, double reviews) throws SQLException {
            String query = "INSERT INTO books (book_name, genres, author, quantity, price, reviews) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, b_name);
            preparedStatement.setString(2, genres);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setDouble(5, price);
            preparedStatement.setDouble(6, reviews);
            return preparedStatement.executeUpdate();
        }

        public static int borrowBook(int b_id, int u_id, Date borrowDate) throws SQLException {
            String query = "INSERT INTO userwithbook (b_id, u_id, borrow_date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, b_id);
            preparedStatement.setInt(2, u_id);
            preparedStatement.setDate(3, new java.sql.Date(borrowDate.getTime()));
            return preparedStatement.executeUpdate();
        }

        public static ResultSet checkUserWithBook(int b_id, int u_id) throws SQLException {
            String query = "SELECT COUNT(*) FROM userwithbook WHERE b_id = ? AND u_id = ?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, b_id);
            preparedStatement.setInt(2, u_id);
            return preparedStatement.executeQuery();
        }

        public static ResultSet getBookQuantity(int b_id) throws SQLException {
            String query = "SELECT quantity FROM books WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, b_id);
            return preparedStatement.executeQuery();
        }

        public static int updateBookQuantity(int b_id, int quantity) throws SQLException {
            String query = "UPDATE books SET quantity = ? WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, b_id);
            return preparedStatement.executeUpdate();
        }

        public static int returnBook(int b_id, int u_id) throws SQLException {
            String query = "DELETE FROM userwithbook WHERE b_id=? AND u_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, b_id);
            preparedStatement.setInt(2, u_id);
            return preparedStatement.executeUpdate();
        }

        public static ResultSet getBookByName(String book_name) throws SQLException {
            String query = "SELECT * FROM books WHERE book_name=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, book_name);
            return preparedStatement.executeQuery();
        }

        public static int updateBookname(int b_id, String newValue) throws SQLException {
            String query = "UPDATE books SET book_name = ? WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, b_id);
            return preparedStatement.executeUpdate();
        }

        public static int updateBookgeners(int b_id, String newValue) throws SQLException {
            String query = "UPDATE books SET geners = ? WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, b_id);
            return preparedStatement.executeUpdate();
        }

        public static int updateBookauthor(int b_id, String newValue) throws SQLException {
            String query = "UPDATE books SET author = ? WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, b_id);
            return preparedStatement.executeUpdate();
        }

        public static int updateBookquantity(int b_id, int newValue) throws SQLException {
            String query = "UPDATE books SET quantity = ? WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, newValue);
            preparedStatement.setInt(2, b_id);
            return preparedStatement.executeUpdate();
        }

        public static int updateBookreviews(int b_id, double newValue) throws SQLException {
            String query = "UPDATE books SET Reviews = ? WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setDouble(1, newValue);
            preparedStatement.setInt(2, b_id);
            return preparedStatement.executeUpdate();
        }

        public static int updateBookprice(int b_id, double newValue) throws SQLException {
            String query = "UPDATE books SET price = ? WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setDouble(1, newValue);
            preparedStatement.setInt(2, b_id);
            return preparedStatement.executeUpdate();
        }

        public static ResultSet getUserById(int u_id) throws SQLException {
            String query = "SELECT COUNT(*) FROM adminuser WHERE u_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, u_id);
            return preparedStatement.executeQuery();
        }

        public static ResultSet getBookById(int b_id) throws SQLException {
            String query = "SELECT COUNT(*) FROM books WHERE b_id=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, b_id);
            return preparedStatement.executeQuery();
        }

        public static ResultSet getAdminByIdAndPassword(int u_id, String password) throws SQLException {
            String query = "SELECT isadmin FROM adminuser WHERE u_id=? AND Password=?";
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setInt(1, u_id);
            preparedStatement.setString(2, password);
            return preparedStatement.executeQuery();
        }

        public  static  ResultSet getnamefromadminuser(int u_id) throws  SQLException{
            String namequery="SELECT name FROM adminuser WHERE  u_id=?";
            PreparedStatement preparedStatementname = DatabaseConnection.connection.prepareStatement(namequery);
            preparedStatementname.setInt(1, u_id);
            return  preparedStatementname.executeQuery();
        }
    }

