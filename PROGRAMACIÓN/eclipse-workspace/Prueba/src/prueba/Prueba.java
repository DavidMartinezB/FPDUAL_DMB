package prueba;
import java.sql.*;

public class Prueba {

	public static void main(String args[]){  
	 	String url = "jdbc:mysql://localhost:3306/pokemondb";
	    String user = "root";
	    String password = "Perritocaliente1@#";

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM pokemon")) {

	        ResultSetMetaData rsmd = rs.getMetaData();
	        int numColumns = rsmd.getColumnCount();

	        while (rs.next()) {
	            for (int i = 1; i <= numColumns; i++) {
	                System.out.print(rs.getString(i) + "\t");
	            }
	            System.out.println();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}  

}
