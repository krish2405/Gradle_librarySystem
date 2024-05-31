package library.utility;

import library.data.DatabaseConnection;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilityfunctions {


    public static boolean validateStringInput(String input) {
        // Input should not be empty or null
        return input != null && !input.trim().isEmpty();
    }

    public static boolean validateQuantity(int quantity) {
        // Quantity should be a positive integer
        return quantity > 0;
    }

    public static boolean validateReviews(double reviews) {
        // Reviews should be in the range 0 to 5
        return reviews >= 0 && reviews <= 5;
    }

    // Validations
    public static boolean validateName(String name) {
        // Name should not be empty
        return !name.trim().isEmpty();
    }

    public static boolean validatePassword(String password) {
        // Password should have at least one special character
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean validateEmail(String email) {
        // Email should contain '@'
        return email.contains("@");
    }

    // Check if table exists
    public static boolean tableExists(String tableName) {
        try {
            String schemaname="library";
            DatabaseMetaData metaData = DatabaseConnection.connection.getMetaData();
            ResultSet resultSet = metaData.getTables(schemaname, null, tableName, null);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
