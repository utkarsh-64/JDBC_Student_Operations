//StudentOperations.java
import java.sql.*;
import java.util.*;

//StudentOperations.java
public class StudentOperations {
    //Establishing the connection
    private static final String URL = "jdbc:mysql://localhost:3306/db_java";
    private static final String USER = "root";
    private static final String PASSWORD = "2407";

    //try is mandatory for the sql code/database connection codes
    public static void insertStudent(){
        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); //return a connection object that will be assigned to "conn"

            //Statement object creation: 1. Statement: for static queries, 2. Prepared statement: for dynamic queries(user input), 3.Callable Statements: for stored procedures and methods
            PreparedStatement stmt = conn.prepareStatement("insert into student (PRN, name, branch, batch, cgpa) values (?, ?, ?, ?, ?)");

            Scanner scan = new Scanner(System.in);

            System.out.println("Enter the PRN: ");
            int PRN = scan.nextInt();

            scan.nextLine(); //to consume the next line characters stored in buffer.

            System.out.println("Enter the name: ");
            String name = scan.nextLine();

            System.out.println("Enter the branch: ");
            String branch = scan.nextLine();

            System.out.println("Enter the Batch: ");
            String batch = scan.nextLine();

            System.out.println("Enter the CGPA: ");
            float cgpa = scan.nextFloat();

            stmt.setInt(1, PRN);
            stmt.setString(2, name);
            stmt.setString(3, branch);
            stmt.setString(4, batch);
            stmt.setFloat(5, cgpa);

            int rowInserted = stmt.executeUpdate();
            if(rowInserted>0){
                System.out.println("Row inserted successfully");
            }

            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayStudent(){
        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); //return a connection object that will be assigned to "conn"
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery("select * from student");

            System.out.println("\nStudent Records: \n");

            while(rs.next()){
                System.out.println("PRN: "+rs.getInt(1));
                System.out.println("Name: "+rs.getString(2));
                System.out.println("Branch: "+rs.getString(3));
                System.out.println("Batch: "+rs.getString(4));
                System.out.println("CGPA: "+rs.getFloat(5) + "\n");
            }

            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteStudent() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE PRN = ?");

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the PRN of the student to delete: ");
            int prn = scan.nextInt();

            stmt.setInt(1, prn);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with the given PRN.");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchByPRN() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student WHERE PRN = ?");

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter PRN to search: ");
            int prn = scan.nextInt();

            stmt.setInt(1, prn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\nStudent Found:");
                System.out.println("PRN: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Branch: " + rs.getString(3));
                System.out.println("Batch: " + rs.getString(4));
                System.out.println("CGPA: " + rs.getFloat(5) + "\n");
            } else {
                System.out.println("\nNo student found with PRN: " + prn);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchByName() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student WHERE name LIKE ?");

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter name to search: ");
            String name = scan.nextLine();

            stmt.setString(1, "%" + name + "%"); // Allows partial matches
            ResultSet rs = stmt.executeQuery();

            boolean found = false;
            System.out.println("\nSearch Results:");
            while (rs.next()) {
                found = true;
                System.out.println("PRN: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Branch: " + rs.getString(3));
                System.out.println("Batch: " + rs.getString(4));
                System.out.println("CGPA: " + rs.getFloat(5) + "\n");
            }

            if (!found) {
                System.out.println("\nNo students found with name containing: " + name);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
