package library.DOA;

public class QueryOperation {
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS adminuser (u_id INT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(50), Password VARCHAR(50), age INT, DOB DATE, Signup_date DATE, Modification_date DATE, email VARCHAR(50), isadmin BOOLEAN)";
    public static final String LOGIN = "SELECT isadmin,u_id FROM adminuser WHERE name = ? AND Password = ?";
    public static final String INSERT_USER = "INSERT INTO adminuser (name, Password, age, DOB, Signup_date, Modification_date, email, isadmin) VALUES (?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE, ?, ?)";
    public static final String SHOW_ALL_USERS = "SELECT * FROM adminuser";
    public static final String USER_INFO = "SELECT * FROM adminuser WHERE name=? AND u_id=?";
    public static final String DELETE_USER = "DELETE FROM adminuser WHERE name=? and u_id=?";
    public static final String CHECK_USER_WITH_BOOK = "SELECT COUNT(*) FROM userwithbook WHERE u_id = ?";
    public static final String UPDATE_USER_NAME = "UPDATE adminuser SET name=?, Modification_date=? WHERE u_id=? AND Password=?";
    public static final String UPDATE_USER_PASSWORD = "UPDATE adminuser SET Password=?, Modification_date=? WHERE u_id=? AND Password=?";
    public static final String UPDATE_USER_DOB = "UPDATE adminuser SET DOB=?, Modification_date=? WHERE u_id=? AND Password=?";
    public static final String UPDATE_USER_EMAIL = "UPDATE adminuser SET email=?, Modification_date=? WHERE u_id=? AND Password=?";
}
