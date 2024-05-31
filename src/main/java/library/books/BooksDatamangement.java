package library.books;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import library.DOAforBooks.BooksDataAccess;
import  library.data.DatabaseConnection;
import library.utility.Utilityfunctions;


public class BooksDatamangement {

    public static void createTableIfNotExists(String tableName, String columnDefinitions) {
        try {
            if (!BooksDataAccess.tableExists(tableName)) {
                BooksDataAccess.createBooksTable(tableName, columnDefinitions);
                System.out.println("Table created successfully.");
            } else {
                System.out.println("Table already exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ShowAllBooks(){
        try {
            ResultSet rs= BooksDataAccess.getAllBooks();

            System.out.println("Here is the information.");
            while(rs.next()){

                System.out.print("Book_id : "+ rs.getString(1)+", ");
                System.out.print(" Book_Name : "+ rs.getString(2)+", ");
                System.out.print("Geners : "+ rs.getString(3)+", ");
                System.out.print("author : "+rs.getString(4)+", ");
                System.out.print("quantity : "+ rs.getString(5)+", ");
                System.out.print("Price : "+ rs.getString(6)+", ");
                System.out.println("Reviews : "+rs.getString(7)+".");

            }


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    // insert book info into books table
    @SuppressWarnings("resource")
    public static void insertBooksData() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Book name :");
            String b_name = scanner.nextLine();
            if (!Utilityfunctions.validateStringInput(b_name)) {
                System.out.println("Book name cannot be empty.");
                return;
            }

            System.out.println("Enter Geners:");
            String geners = scanner.nextLine();
            if (!Utilityfunctions.validateStringInput(geners)) {
                System.out.println("Genre cannot be empty.");
                return;
            }


            System.out.println("Enter author:");
            String author = scanner.nextLine();
            if (!Utilityfunctions.validateStringInput(author)) {
                System.out.println("Author cannot be empty.");
                return;
            }

            System.out.println("Enter quantity:");
            int quantity = scanner.nextInt();
            if (!Utilityfunctions.validateQuantity(quantity)) {
                System.out.println("Quantity must be a positive integer.");
                return;
            }


            System.out.println("Enter Price:");
            Double price = scanner.nextDouble();

            System.out.println("Enter reviews (0 -5) ");
            Double Reviews = scanner.nextDouble();
            if (!Utilityfunctions.validateReviews(Reviews)) {
                System.out.println("Reviews must be in the range 0 to 5.");
                return;
            }

            int rowinserted = BooksDataAccess.insertBook(b_name,geners,author,quantity,price,Reviews);
            if (rowinserted > 0) {
                System.out.println("Insertion successful");
            }

            System.out.println("Data inserted successfully.");
            // scanner.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    public static void borrow(){
        try {
            Scanner sc=new Scanner(System.in);


            System.out.println("Enter the Book id : ");
            int b_id=sc.nextInt();

            System.out.println("Enter the User id : ");
            int u_id=sc.nextInt();

            ResultSet idc = BooksDataAccess.getUserById(u_id);
            idc.next();
            int count1 = idc.getInt(1);

            if (count1 == 0) {
                System.out.println("No user found");
                // sc.close();
                return;

            }
            ResultSet bidc = BooksDataAccess.getBookById(b_id);
            bidc.next();
            int countr = bidc.getInt(1);
            if (countr == 0) {
                System.out.println("No Book found");
                // sc.close();
                return;

            }

            ResultSet checkResult = BooksDataAccess.checkUserWithBook(b_id,u_id);
            checkResult.next();
            int count = checkResult.getInt(1);

            if (count > 0) {
                System.out.println("User already has the book.");
            } else {
                sc.nextLine();
                ResultSet rs=BooksDataAccess.getBookQuantity(b_id);
                if (rs.next()) {

                    int quant=Integer.parseInt(rs.getString(1)) ;
                    if(quant>0){
                        int newquantity=quant-1;
                        int count2=BooksDataAccess.updateBookQuantity(b_id,newquantity);
                        System.out.println(count2);

                        // Get the current date for borrow_date
                        java.util.Date currentDate = new java.util.Date();
                        java.sql.Date borrowDate = new java.sql.Date(currentDate.getTime());

                        // Add the borrowed book to the userwithbook table
                        int insertCount = BooksDataAccess.borrowBook(b_id,u_id,borrowDate);
                        System.out.println(insertCount);


                    }
                    else{
                        System.out.println("Sorry book is out of stock ");
                    }

                }
                else{
                    System.out.println("No book found");
                }
            } }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Returning book
    @SuppressWarnings("resource")
    public static void returnbook(){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the book id: ");
            int b_id=sc.nextInt();

            System.out.println("Enter your id: ");
            int u_id=sc.nextInt();

            ResultSet idc = BooksDataAccess.getUserById(u_id);
            idc.next();
            int count1 = idc.getInt(1);

            if (count1 == 0) {
                System.out.println("No user found");
                // sc.close();
                return;

            }

            ResultSet bidc = BooksDataAccess.getBookById(b_id);
            bidc.next();
            int countr = bidc.getInt(1);
            if (countr == 0) {
                System.out.println("No Book found");
                // sc.close();
                return;

            }
            ResultSet checkResult = BooksDataAccess.checkUserWithBook(b_id,u_id);
            checkResult.next();
            int count = checkResult.getInt(1);

            if (count > 0) {
                // System.out.println("User already has the book.");}
                ResultSet rs=BooksDataAccess.getBookQuantity(b_id);
                ResultSet rs2=BooksDataAccess.getnamefromadminuser(u_id);

                if (rs.next() && rs2.next()) {
                    int quant=Integer.parseInt(rs.getString(1)) ;
                    int newquantity=quant+1;

                    int count12=BooksDataAccess.updateBookQuantity(b_id,newquantity);
                    System.out.println(count12);

                    int insertCount = BooksDataAccess.returnBook(b_id,u_id);
                    System.out.println(insertCount);


                }
                else{
                    System.out.println("No book found");
                }
            }
            else{
                System.out.println("You don't have any book to return.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Book information
    @SuppressWarnings("resource")
    public static void Bookinfo(){
        try {
            Scanner sc =new Scanner(System.in);
            System.out.println("Enter Book name:");
            String book_name=sc.nextLine();
            ResultSet rs=BooksDataAccess.getBookByName(book_name);
            if(rs.next()){
                System.out.println("Here is the information.");
                System.out.print("id : "+ rs.getString(1)+", ");
                System.out.print("Book name : "+ rs.getString(2)+", ");
                System.out.print("Gener : "+ rs.getString(3)+", ");
                System.out.print("Author : "+rs.getString(4)+", ");
                System.out.print("Quantity : "+ rs.getString(5)+", ");
                System.out.print("Price : "+ rs.getString(6)+", ");
                System.out.println("Reviews : "+rs.getString(7)+".");

            }
            else{
                System.out.println("Book not found in database ");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    public static void Update_Book(){
        try {

            Scanner sc =new Scanner(System.in);
            System.out.println("Enter Your U_id");
            int u_id=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Your Password");
            String Password=sc.nextLine();

            ResultSet adminlogin=BooksDataAccess.getAdminByIdAndPassword(u_id,Password);

            if(adminlogin.next()){
                System.out.println(adminlogin.getString(1));
                if(adminlogin.getString(1).equals("1")){


                    System.out.println("Enter field to update :");

                    System.out.println("1.)Book Name 2.) Gener 3.) Author 4.) Quantity 5) Price  6.)Reviews 7.) Exit ");
                    int choice=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Your b_id");
                    int b_id=sc.nextInt();

                    switch (choice) {


                        case 1:
                            sc.nextLine();
                            System.out.println("Enter Your Book Name");
                            String name=sc.nextLine();

                            int rowsUpdatedName = BooksDataAccess.updateBookname(b_id,name);
                            if (rowsUpdatedName > 0) {
                                System.out.println("Book name updated successfully.");
                            }
                            else{
                                System.out.println("Sorry Password or User id does not match");
                            }
                            break;

                        case 2:
                            sc.nextLine();
                            System.out.println("Enter Geners:");
                            String gener = sc.nextLine();

                            int rowsUpdatedAge = BooksDataAccess.updateBookgeners(b_id,gener);
                            if (rowsUpdatedAge > 0) {
                                System.out.println("Geners updated successfully.");
                            }
                            else{
                                System.out.println("Sorry Password or User id does not match");
                            }
                            break;

                        case 3:
                            sc.nextLine();
                            System.out.println("Enter Author:");
                            String author = sc.nextLine();

                            int rowsUpdatedPassword = BooksDataAccess.updateBookauthor(b_id,author);
                            if (rowsUpdatedPassword > 0) {
                                System.out.println("Password updated successfully.");
                            }
                            else{
                                System.out.println("Sorry Password or User id does not match");
                            }
                            break;

                        case 4:
                            sc.nextLine();
                            System.out.println("Quantity");
                            int Quantity = sc.nextInt();

                            int rowupdatequant = BooksDataAccess.updateBookQuantity(b_id,Quantity);
                            if (rowupdatequant > 0) {
                                System.out.println("Quantity updated successfully.");
                            }
                            else{
                                System.out.println("Sorry Password or User id does not match");
                            }
                            break;

                        case 5:
                            sc.nextLine();
                            System.out.println("Enter price:");
                            Double Price = sc.nextDouble();

                            int rowsUpdatedprice = BooksDataAccess.updateBookprice(b_id,Price);
                            if (rowsUpdatedprice > 0) {
                                System.out.println("Price updated successfully.");
                            }
                            else{
                                System.out.println("Sorry Password or User id does not match");
                            }
                            break;

                        case 6:
                            sc.nextLine();
                            System.out.println("Enter Reviews (1 to 5):");
                            Double Reviews = sc.nextDouble();
                            int rowsupdatereviews = BooksDataAccess.updateBookreviews(b_id,Reviews);
                            if (rowsupdatereviews > 0) {
                                System.out.println("Price updated successfully.");
                            }
                            else{
                                System.out.println("Sorry Password or User id does not match");
                            }
                            break;

                        default:
                            break;
                    }


                } }
            else{
                System.out.println("You are not admin");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
