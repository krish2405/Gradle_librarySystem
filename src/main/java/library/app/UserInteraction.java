package library.app;
import java.util.InputMismatchException;
import java.util.Scanner;
import  library.books.BooksDatamangement;
import library.businesslogic.UserBookDataManagement;
import library.data.DatabaseConnection;
import library.userdata.UserDatamanagement;
//import java.util.Scanner;


public class UserInteraction {

    public static void main(String[] args) {
        DatabaseConnection.connect();

        Scanner sc= new Scanner(System.in);
        String tablenamebooks="Books";
        String columndefintion="b_id INT AUTO_INCREMENT PRIMARY KEY, "+
                "book_name VARCHAR(255), "+
                "geners VARCHAR(255), "+
                "author VARCHAR(255), "+
                "quantity INT, "+
                "price DECIMAL(10,2), "+
                "Reviews DECIMAL(10,2) ";

        String tableNameadminuser = "adminuser";
        String columnDefinitionforadminuser = "u_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "Password VARCHAR(255) NOT NULL, " +
                "age INT, " +
                "DOB DATE, " +
                "Signup_date DATE, " +
                "Modification_date DATE, " +
                "email VARCHAR(255), " +
                "isadmin BOOLEAN";

        String tablenameuserbook="userwithbook";
        String columndefintionuserbook="bb_id INT AUTO_INCREMENT PRIMARY KEY, "+
                "b_id INT, "+
                "u_id INT, "+
                "return_date DATE, "+
                "borrow_date DATE , "+
                "FOREIGN KEY (b_id) REFERENCES Books(b_id), "+
                "FOREIGN KEY (u_id) REFERENCES adminuser(u_id)";




        BooksDatamangement.createTableIfNotExists(tablenamebooks,columndefintion);
        int admintablecheck=UserDatamanagement.createTableIfNotExists(tableNameadminuser, columnDefinitionforadminuser);
        if(admintablecheck==1){
            System.out.println("new table created so creating a dummy admin");
            System.out.println("We are creating a dummy admin for you so that you can have all the privileges");
            UserDatamanagement.createadmin();


        }
        else{
            System.out.println("you have the table in db");
        }
        UserBookDataManagement.createTableIfNotExists(tablenameuserbook,columndefintionuserbook);




        String logininfo=UserDatamanagement.login();
//        String logininfo = null;
//        try {
//            logininfo = UserDatamanagement.login();
//        } catch (Exception e) {
//            System.out.println("No input found, exiting.");
//            DatabaseConnection.close();
//            sc.close();
//            return;
//        }

        System.out.println(logininfo);

        if (logininfo==null){
            System.out.println("Wrong credentials");
            sc.close();
            return;
        }
        if(logininfo.equals("1")){

            System.out.println("Welcome Admin");

            int k=1;

            while(k==1)
            {
                System.out.println("What do You want to do :");
                System.out.println("1.) Add User,\n 2.)Show All User,\n 3.) Find User Information ,\n 4.) Remove User,\n 5.) Show All Books,\n 6.)Add books ,\n 7.)Find Book Information ,\n 8.) Update User\n 9.) Update Book information\n 10).ALL User with Book info\n 11.) User With book\n  12.) Exit");
                try{

                    int option =sc.nextInt();
                    switch (option) {
                        case 1:
                            UserDatamanagement.insertData();
                            break;

                        case 2:
                            UserDatamanagement.ShowAlluser();
                            break;
                        case 3:
                            UserDatamanagement.USerinfo();
                            break;
                        case 4:
                            UserDatamanagement.deleteUserData();
                            break;
                        case 5:
                            BooksDatamangement.ShowAllBooks();
                            break;

                        case 6:
                            BooksDatamangement.insertBooksData();
                            break;

                        case 7:
                            BooksDatamangement.Bookinfo();
                            break;

                        case 8:
                            UserDatamanagement.Update_user();
                            break;

                        case 9:
                            BooksDatamangement.Update_Book();
                            break;

                        case 10:
                            UserBookDataManagement.alluserwithbookinfo();
                            break;

                        case 11:
                            UserBookDataManagement.userswithbookinfo();
                            break;



                        default:
                            k=0;
                            break;
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                    // break;

                }
            }
        }
        if(logininfo.equals("0")){
            System.out.println("Welcome User");

            int u=1;

            while(u==1)
            {
                System.out.println("What do You want to do :");
                System.out.println("1.) Borrow ,\n 2.)Return ,\n  3.) User Information,\n 4.) Show All Books,\n 5.) Show All USer with Books,\n 6.) Show all Users,\n 7.)Update Information,\n 8.)Exit");
                try{

                    int option =sc.nextInt();
                    switch (option) {
                        case 1:
                            BooksDatamangement.borrow();
                            break;

                        case 2:
                            BooksDatamangement.returnbook();;
                            break;
                        case 3:
                            UserDatamanagement.USerinfo();
                            break;

                        case 4:
                            BooksDatamangement.ShowAllBooks();
                            break;
                        case 5:
                            UserBookDataManagement.alluserwithbookinfo();
                            break;

                        case 6:
                            UserDatamanagement.ShowAlluser();
                            break;

                        case 7:
                            UserDatamanagement.Update_user();
                            break;

                        default:

                            u=0;
                            break;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                }

            }
        }
        sc.close();
        DatabaseConnection.close();
    }

}
