package library.businesslogic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import library.data.DatabaseConnection;
import library.utility.Utilityfunctions;
import library.DOAforuserwithbooks.userBookDataAccess;

public class UserBookDataManagement {
    public static void createuserwithbookTable(String tableName, String columnDefinitions) {
        try {
            userBookDataAccess.createUserWithBookTable(tableName,columnDefinitions);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create table if it doesn't exist
    public static void createTableIfNotExists(String tableName, String columnDefinitions) {
        if (!Utilityfunctions.tableExists(tableName)) {
            createuserwithbookTable(tableName, columnDefinitions);
        } else {
            System.out.println("Table already exists.");
        }
    }

    @SuppressWarnings("resource")
    public static void userswithbookinfo(){
        try {
            Scanner sc =new Scanner(System.in);


            System.out.println("Enter user ID:");
            int u_id=sc.nextInt();
            ResultSet rs3ResultSet = userBookDataAccess.getUserBookIds(u_id);
            while(rs3ResultSet.next()){
                int b_id=Integer.parseInt( rs3ResultSet.getString(1));
                ResultSet resultSet=userBookDataAccess.getAllUserBookInfo(u_id,b_id);

                while (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    String username = resultSet.getString(2);
                    int bookId = resultSet.getInt(3);
                    String bookTitle = resultSet.getString(4);
                    String date=resultSet.getString(5);

                    System.out.println("User ID: " + userId + ", Username: " + username + ", Book ID: " + bookId
                            + ", Title: " + bookTitle + " ,borrow_date: " + date);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void alluserwithbookinfo(){
        try{
            ResultSet rs3ResultSet = userBookDataAccess.getAllUserIdsWithBooks();

            while(rs3ResultSet.next()){
                int u_id=Integer.parseInt(rs3ResultSet.getString(1));
                ResultSet B_rs3ResultSet = userBookDataAccess.getBookIdsByUser(u_id);
                while(B_rs3ResultSet.next()){
                    int b_id=Integer.parseInt( B_rs3ResultSet.getString(1));
                    ResultSet resultSet=userBookDataAccess.getAllUserBookInfo(u_id,b_id);
                    while (resultSet.next()) {
                        int userId = resultSet.getInt(1);
                        String username = resultSet.getString(2);
                        int bookId = resultSet.getInt(3);
                        String bookTitle = resultSet.getString(4);
                        String date=resultSet.getString(5);

                        System.out.println("User ID: " + userId + ", Username: " + username + ", Book ID: " + bookId
                                + ", Title: " + bookTitle+ " ,borrow_date: " + date);
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
