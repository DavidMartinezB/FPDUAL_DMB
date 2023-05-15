package prueba;
import java.sql.*;
import java.util.Scanner;

public class Prueba {

	public static void main(String args[]) throws SQLException{  
	 	String url = "jdbc:mysql://localhost:3306/pokemondb";
	    String user = "root";
	    String password = "Perritocaliente1@#";
	    int choice;
	    Scanner sc = new Scanner(System.in);
	    choice = sc.nextInt();
	    
	    do {
			switch (choice) {
			case 1: {
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
				choice = sc.nextInt();
			}
			case 2: {
				Connection conn = DriverManager.getConnection(url, user, password);
				String query = "INSERT INTO pokemon(numero_pokedex, nombre, peso, altura) VALUES ('pepe', 1, 2)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.executeUpdate();
				
				Statement state = conn.createStatement();
				ResultSet rs = state.executeQuery("SELECT * FROM pokemon"); {
					ResultSetMetaData rsmd = rs.getMetaData();
					int numColums = rsmd.getColumnCount();
					
					while (rs.next()) {
						for (int i = 1; i <= numColums; i++) {
							System.out.print(rs.getString(i)+ "\t");
						}
						System.out.println();
					}
				};
			}
			case 3: {
				
				
			}
			case 4: {
				
				
			}
			default:
				throw new IllegalArgumentException("Valor invalido:"+ choice);
			}
		} while (choice != 0);

	}  

}
