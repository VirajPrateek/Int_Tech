<<<<<<< HEAD
/*
MySQL in WAMP
uses mysql-connector-5.1.44
@Written in:: NetBeans8.2
@First Complete:: 09/11/17
@author :: Kumar Prateek Viraj
*/
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
        int total;
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
    void AverageInSubject(){
        try{
       for(int i=1;i<4;i++){
       String subAverage="SELECT AVG(sub"+i+") FROM result";
       stmt=conn.createStatement();
       ResultSet subav=stmt.executeQuery(subAverage);
       subav.next();
        System.out.println("Average marks in subj"+i+":: "+subav.getInt(1));
       }
       }catch(SQLException e){
       e.printStackTrace();
       }
   }
   String findTopper(){
        String topper=null;
        int roll=0;
        String query="SELECT roll,name FROM result WHERE total=("
                     + "SELECT MAX(total) FROM result)";
        try{
        stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
        roll=rs.getInt(1);
        topper=rs.getString(2);
        } System.out.println("Roll is: "+roll);
        }catch(SQLException e){
        System.out.println("Error!");
        }
      return topper;
   }
   void secondThird(){
       int topperRoll=0;
       String name=null;
       String queryForSecond="SELECT roll,name "
                   + "FROM result ORDER BY total DESC LIMIT 1,1";
       String queryForThird="SELECT roll,name "
                   + "FROM result ORDER BY total DESC LIMIT 2,1";
       try{
           stmt=conn.createStatement();
           ResultSet rs1 = stmt.executeQuery(queryForSecond);
            while(rs1.next()){
            topperRoll=rs1.getInt(1);
            name=rs1.getString(2);
            System.out.println("Second Topper:: Name : "+name+" Roll: "+topperRoll);
           }
            ResultSet rs2 = stmt.executeQuery(queryForThird);
           while(rs2.next()){
            topperRoll=rs2.getInt(1);
            name=rs2.getString(2);
            System.out.println("\nThird Topper:: Name : "+name+" Roll: "+topperRoll);
           }
       }catch(SQLException ex){
       System.out.println("Some error "+ex);
       }
       
   }
   void subjectToppers(int subjNum){
       String topper=null;
       String query="SELECT roll,name FROM result WHERE sub"+subjNum+"=("
                     + "SELECT MAX(sub"+subjNum+") FROM result)";
       try{
           stmt=conn.createStatement();
           ResultSet rs=stmt.executeQuery(query);
           while(rs.next()){
           System.out.println("Subj"+subjNum+" Topper::"
                   + " Name"+rs.getString(2)+" Roll no: "+rs.getInt(1));
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
   }
   void averageMarks(){
      try{
       String subAverage="SELECT AVG(total) FROM result";
       stmt=conn.createStatement();
       ResultSet subav=stmt.executeQuery(subAverage);
       subav.next();
        System.out.println("Average Total marks:: "+subav.getInt(1));
       }catch(SQLException e){
       e.printStackTrace();
       }
   
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
             case 3: s.AverageInSubject();
             break;
             case 4: System.out.println("Topper is: "+s.findTopper());
             break;
             case 5: System.out.println("First Topper:: "+s.findTopper());
                     s.secondThird();
             break;
             case 6: s.subjectToppers(1);
                     s.subjectToppers(2);
                     s.subjectToppers(3);
             break;
             case 7:s.averageMarks(); 
             break;
             case 8: s.secondThird();
             break;
             case 0: s.closeConnection();
                        System.exit(0);
         }
         }while(choice!=0);
         s.closeConnection();
       }
=======
/*
MySQL in WAMP
uses mysql-connector-5.1.44
@Written in:: NetBeans8.2
@First Complete:: 09/11/17
@author :: Kumar Prateek Viraj
*/
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
        int total;
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
    void AverageInSubject(){
        try{
       for(int i=1;i<4;i++){
       String subAverage="SELECT AVG(sub"+i+") FROM result";
       stmt=conn.createStatement();
       ResultSet subav=stmt.executeQuery(subAverage);
       subav.next();
        System.out.println("Average marks in subj"+i+":: "+subav.getInt(1));
       }
       }catch(SQLException e){
       e.printStackTrace();
       }
   }
   String findTopper(){
        String topper=null;
        int roll=0;
        String query="SELECT roll,name FROM result WHERE total=("
                     + "SELECT MAX(total) FROM result)";
        try{
        stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
        roll=rs.getInt(1);
        topper=rs.getString(2);
        } System.out.println("Roll is: "+roll);
        }catch(SQLException e){
        System.out.println("Error!");
        }
      return topper;
   }
   void secondThird(){
       int topperRoll=0;
       String name=null;
       String queryForSecond="SELECT roll,name "
                   + "FROM result ORDER BY total DESC LIMIT 1,1";
       String queryForThird="SELECT roll,name "
                   + "FROM result ORDER BY total DESC LIMIT 2,1";
       try{
           stmt=conn.createStatement();
           ResultSet rs1 = stmt.executeQuery(queryForSecond);
            while(rs1.next()){
            topperRoll=rs1.getInt(1);
            name=rs1.getString(2);
            System.out.println("Second Topper:: Name : "+name+" Roll: "+topperRoll);
           }
            ResultSet rs2 = stmt.executeQuery(queryForThird);
           while(rs2.next()){
            topperRoll=rs2.getInt(1);
            name=rs2.getString(2);
            System.out.println("\nThird Topper:: Name : "+name+" Roll: "+topperRoll);
           }
       }catch(SQLException ex){
       System.out.println("Some error "+ex);
       }
       
   }
   void subjectToppers(int subjNum){
       String topper=null;
       String query="SELECT roll,name FROM result WHERE sub"+subjNum+"=("
                     + "SELECT MAX(sub"+subjNum+") FROM result)";
       try{
           stmt=conn.createStatement();
           ResultSet rs=stmt.executeQuery(query);
           while(rs.next()){
           System.out.println("Subj"+subjNum+" Topper::"
                   + " Name"+rs.getString(2)+" Roll no: "+rs.getInt(1));
           }
       }catch(SQLException e){
           e.printStackTrace();
       }
   }
   void averageMarks(){
      try{
       String subAverage="SELECT AVG(total) FROM result";
       stmt=conn.createStatement();
       ResultSet subav=stmt.executeQuery(subAverage);
       subav.next();
        System.out.println("Average Total marks:: "+subav.getInt(1));
       }catch(SQLException e){
       e.printStackTrace();
       }
   
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
             case 3: s.AverageInSubject();
             break;
             case 4: System.out.println("Topper is: "+s.findTopper());
             break;
             case 5: System.out.println("First Topper:: "+s.findTopper());
                     s.secondThird();
             break;
             case 6: s.subjectToppers(1);
                     s.subjectToppers(2);
                     s.subjectToppers(3);
             break;
             case 7:s.averageMarks(); 
             break;
             case 8: s.secondThird();
             break;
             case 0: s.closeConnection();
                        System.exit(0);
         }
         }while(choice!=0);
         s.closeConnection();
       }
>>>>>>> ebd2eb7026032ffa05ef97921b43a8fbfdde6887
}