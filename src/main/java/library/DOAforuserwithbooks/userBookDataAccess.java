package library.DOAforuserwithbooks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.data.DatabaseConnection;
import library.utility.Utilityfunctions;


public class userBookDataAccess {

    public static void createUserWithBookTable(String tableName, String columnDefinitions) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columnDefinitions + ")";
        DatabaseConnection.statement.executeUpdate(query);
    }

    public static boolean tableExists(String tableName) throws SQLException {
        return Utilityfunctions.tableExists(tableName);
    }

    public static ResultSet getUserBookIds(int u_id) throws SQLException {
        String b_idquery = "SELECT b_id FROM userwithbook WHERE u_id=?";
        PreparedStatement b_iPreparedStatement = DatabaseConnection.connection.prepareStatement(b_idquery);
        b_iPreparedStatement.setInt(1, u_id);
        return b_iPreparedStatement.executeQuery();
    }

    public static ResultSet getUserBookInfo(int u_id, int b_id) throws SQLException {
        String querym = "SELECT DISTINCT u.u_id, u.name, b.b_id, b.book_name, ub.borrow_date " +
                "FROM adminuser u " +
                "JOIN userwithbook ub ON u.u_id = ub.u_id " +
                "JOIN books b ON ub.b_id = b.b_id " +
                "WHERE u.u_id = ? AND b.b_id = ?";
        PreparedStatement qPreparedStatement = DatabaseConnection.connection.prepareStatement(querym);
        qPreparedStatement.setInt(1, u_id);
        qPreparedStatement.setInt(2, b_id);
        return qPreparedStatement.executeQuery();
    }

    public static ResultSet getAllUserIdsWithBooks() throws SQLException {
        String idquery = "SELECT DISTINCT u_id FROM userwithbook";
        PreparedStatement iPreparedStatement = DatabaseConnection.connection.prepareStatement(idquery);
        return iPreparedStatement.executeQuery();
    }

    public static ResultSet getBookIdsByUser(int u_id) throws SQLException {
        String b_idquery = "SELECT b_id FROM userwithbook WHERE u_id=?";
        PreparedStatement b_iPreparedStatement = DatabaseConnection.connection.prepareStatement(b_idquery);
        b_iPreparedStatement.setInt(1, u_id);
        return b_iPreparedStatement.executeQuery();
    }

    public static ResultSet getAllUserBookInfo(int u_id, int b_id) throws SQLException {
        String querym = "SELECT u.u_id, u.name, b.b_id, b.book_name, ub.borrow_date " +
                "FROM adminuser u " +
                "JOIN userwithbook ub ON u.u_id = ub.u_id " +
                "JOIN books b ON ub.b_id = b.b_id " +
                "WHERE u.u_id = ? AND b.b_id = ?";
        PreparedStatement qPreparedStatement = DatabaseConnection.connection.prepareStatement(querym);
        qPreparedStatement.setInt(1, u_id);
        qPreparedStatement.setInt(2, b_id);
        return qPreparedStatement.executeQuery();
    }
}
