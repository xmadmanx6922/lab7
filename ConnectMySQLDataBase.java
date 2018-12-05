import java.sql.*;

public class ConnectMySQLDataBase {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/bazzzoka";

   static final String USER = "awronski";
   static final String PASS = "123konstytucja";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   Boolean baseExist = false;
   String sql;
  
   try{
      Class.forName("com.mysql.cj.jdbc.Driver");
	   
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);	   
      	   
      System.out.println("Check if table in base exist");
      DatabaseMetaData md = conn.getMetaData();
      ResultSet rs = md.getTables(null, null, "Cars", null);
      while (rs.next()) {
            System.out.println("Table Exist");
	    baseExist = true;
      }
      rs = null;  
      if(!baseExist){
      	System.out.println("Creating Table");
      	stmt = conn.createStatement();
      	sql = "CREATE TABLE Cars (CarsID int, VehicleBrand varchar(255), Model varchar(255), Engine varchar(255), Transmission varchar(255) )";
      	stmt.executeUpdate(sql);
      	stmt = null;
      }
	   
      stmt = conn.createStatement();
      System.out.println("Inserting Data to Table");
      sql = "INSERT INTO Cars (CarsID, VehicleBrand, Model, Engine, Transmission) VALUES (1, 'Hyundai', 'Coupe', '2.0 B','Manual'), (2, 'Hyundai', 'IX35', '1.6 B','Manual'), (3, 'Citroen', 'C3', '1.4 D','Manual')";
      stmt.executeUpdate(sql);	 
      stmt = null;
	   
      stmt = conn.createStatement();
      sql = "SELECT CarsID, VehicleBrand, Model, Engine, Transmission FROM Cars";
      rs = stmt.executeQuery(sql);

      while(rs.next()){
         int id  = rs.getInt("CarsID");
         String first = rs.getString("VehicleBrand");
         String last = rs.getString("Model");
	 String address = rs.getString("Engine");
	 String city = rs.getString("Transmission");

         System.out.println("ID: " + id);
         System.out.println(", VehicleBrand: " + first);
         System.out.println(", Model: " + last);
	 System.out.println(", Engine: " + address);
	 System.out.println(", Transmission: " + city);
      }
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}
