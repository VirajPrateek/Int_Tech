import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
class student{
     Connection conn = null;
     Statement stmt = null;
    student(){
         System.out.println("\n\n***** MySQL JDBC Connection Testing *****");
           try
           {
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               System.out.println("ok till line 13");
               String userName = "root";
               String password = "toor";
               String url = "jdbc:mysql://localhost:3306/students?autoReconnect=true&useSSL=false";        
               conn = DriverManager.getConnection (url, userName, password);
               System.out.println ("\nDatabase Connection Established...");
           }
          catch (Exception ex)
           {
                System.err.println ("Cannot connect to database server");
           }           
    }
    void setupDatabase(){
         try{ 
             stmt = conn.createStatement();
             String createDb="CREATE DATABASE IF NOT EXISTS students";
             stmt.executeUpdate(createDb);    
             System.out.println("\nDatabase setup done.");
             String createTable="CREATE TABLE IF NOT EXISTS students.result ("
                            + "roll INT(3) NOT NULL,"
                            + "name VARCHAR(255),"
                            + "sub1 INT(3),"
                            + "sub2 INT(3),"
                            + "sub3 INT (3),"
                            + "total INT(3),"
                            + "PRIMARY KEY(roll)"
                            + ")";
             stmt.executeUpdate(createTable);
             System.out.println("\nTable setup done.");
         }
         catch(SQLException s){ 
             System.out.println("\nProblem in database setup."+s);
         }
    }   
    void inputValues(){
        int total=0;
       // System.out.println("\nEnter Roll: ");
        Scanner reader=new Scanner(System.in);
        //int roll=reader.nextInt();
        System.out.println("\nEnter Name: ");
        String name=reader.next();
        System.out.println("Marks in Subject 1:");
        int sub1=reader.nextInt();
        System.out.println("Marks in Subject 2: ");
        int sub2=reader.nextInt();
        System.out.println("Marks in Subject 3: ");
        int sub3=reader.nextInt();
       total=sub1+sub2+sub3;
        insertValues(name,sub1,sub2,sub3,total);
    }
    void insertValues(String name,int sub1,int sub2,int sub3,int total){
        int rowCount=0;  
        try {
            String insertQuery= "INSERT INTO `result` "
                    + "(`name`, `sub1`, `sub2`, `sub3`, `total`)"
                    + " VALUES(?,?,?,?,?)";
           PreparedStatement pst=conn.prepareStatement(insertQuery);
             pst.setString(1,name);
             pst.setInt(2, sub1);
             pst.setInt(3, sub2);
             pst.setInt(4, sub3);
             pst.setInt(5,total);
             pst.executeUpdate();
             pst.close(); 
           System.out.println("Data Inserted!");
         } catch (SQLException ex) {
            System.out.println("Some error"+ex);
         }
      }
    int totalStudents(){
        int count=0;
        try{
        stmt=conn.createStatement();
        String query="SELECT COUNT(*) FROM students.result";
        ResultSet res =stmt.executeQuery(query);
        while (res.next()){
             count = res.getInt(1);
          }
        }
        catch(Exception e){
            System.out.println("Error!");
        }
        return count;
    }
    int AverageInSubject(){
        int average=0;
        int choice;
        System.out.println("Find Avereage of:\n\n1.Suub1\n2.Sub2\n3.Sub3");
        Scanner reader=new Scanner(System.in);
        choice = reader.nextInt();
        String findAverage = "SELECT SUM(sub"+choice+") FROM result";
        try{
        stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(findAverage);
        rs.next();
        String avg=rs.getString(1);
        average=Integer.parseInt(avg)/totalStudents();
        }catch(Exception e){
            System.out.println("Error!");
        }
        return average; 
    }
   String findTopper(){
        String topper=null;
        String query="SELECT name FROM result WHERE total=("
                     + "SELECT MAX(total) FROM result)";
        try{
        stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        rs.next();
        topper=rs.getString(1);
        }catch(SQLException e){
        System.out.println("Error!");
        }
      return topper;
   }
   void closeConnection(){
        if (conn != null)
              {
               try
                {
                  System.out.println("\n***** Let terminate the Connection *****");
                  conn.close ();					   
                   System.out.println ("\nDatabase connection terminated...");
                   }
                  catch (Exception ex)
                  {
                  System.out.println ("Error in connection termination!");
                  }
               }
   }
}
   public class testjdbc
   {
       public static void main (String[] args)
       {
         int choice;
         student s=new student();
         s.setupDatabase();
         do{
         System.out.println("Hey user! Whatd'y wanna do?");
         System.out.println("\n1.Insert Values \n2.Total Number of Students"
                 + "\n3.Average marks of Subject \n4.Who's the topper?"
                 + "\n5.Who are First, Second & Third\n6.Subjectwise toppers"
                 + "\n7.Average Marks\n8.Who is Second Topper?\n\n0.Close&Exit");
         Scanner reader=new Scanner(System.in);
         choice = reader.nextInt();
         switch(choice){
             case 1: s.inputValues();
             break;
             case 2:System.out.println("\nTotal Students: "+s.totalStudents());
             break;
             case 3: System.out.println("\nAverage marks is "+s.AverageInSubject());
             break;
             case 4: System.out.println("Topper is: "+s.findTopper());
             break;
             case 0: s.closeConnection();
                        System.exit(0);
         }
         }while(choice!=0);
         s.closeConnection();
       }
}