package conectionDB;
import java.sql.*;

public class Connection {
	 
 	private static String url = "jdbc:mysql://localhost:3306/pokemondb";
    private static String user = "root";
    private static String password = "Perritocaliente1@#";
    private static java.sql.Connection conn;
    
    public static java.sql.Connection crearConexion() throws SQLException {
    	conn = DriverManager.getConnection(url, user, password);
    	return conn;
    }
    
    public static void lecturaBD() throws SQLException {
    	conn = crearConexion();
    	try (
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT * FROM pokemon")) {
		        while (rs.next()) {
		            for (int i = 1; i <= 2; i++) {
		                System.out.print(rs.getString(i) + "\t");
		            }
		            System.out.println();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
    }
    public static void actualizarPorNumeroBD( int numero, String nombre) {
    	try {
			conn = crearConexion();
			
			String query = "UPDATE pokemon SET nombre = ? WHERE numero_pokedex = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, nombre);
			ps.setInt(2, numero);
		
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void eliminarPorNumeroBD( int numero) {
    	try {
			conn = crearConexion();
			
			String query = "DELETE FROM pokemon WHERE numero_pokedex = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, numero);
		
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void escrituraBD(String nombre, int peso, int altura) {
    	try {
			conn = crearConexion();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT numero_pokedex FROM pokemon ORDER BY numero_pokedex DESC LIMIT 1");
			
			rs.next();
			int numero_pokedex = rs.getInt("numero_pokedex")+1;
			
			String query = "INSERT INTO pokemon(numero_pokedex, nombre, peso, altura) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, numero_pokedex);
			ps.setString(2, nombre);
			ps.setInt(3, peso);
			ps.setInt(4, altura);
			
			ps.executeUpdate();
			
			System.out.println("El pokemon con numero de pokedex "+numero_pokedex+" ha sido creado con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void leerTipoFuegoBD() {
    	try {
			conn = crearConexion();
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM pokemon as p JOIN pokemon_tipo AS pt ON p.numero_pokedex = pt.numero_pokedex JOIN tipo AS t ON t.id_tipo = pt.id_tipo AND t.nombre = 'fuego'"); 
	        while (rs.next()) {
	            for (int i = 1; i <= 2; i++) {
	                System.out.print(rs.getString(i) + "\t");
	            }
	            System.out.println();
	        }
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void sumarAlturaBD() {
    	try {
			conn = crearConexion();
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT AVG(altura) AS media_altura FROM pokemon"); 
	        rs.next();
	        float altura = rs.getFloat("media_altura");
	        
	        System.out.println("la suma de la altura de todos los pokemon es: "+altura);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void sumarPesoAguaBD() {
    	try {
			conn = crearConexion();
			Statement stmt = conn.createStatement();
			ResultSet rsImp = stmt.executeQuery("SELECT * FROM pokemon AS p JOIN pokemon_tipo AS pt ON p.numero_pokedex = pt.numero_pokedex JOIN tipo AS t ON t.id_tipo = pt.id_tipo AND t.nombre = 'agua' ");
			 while (rsImp.next()) {
		            for (int i = 1; i <= 2; i++) {
		                System.out.print(rsImp.getString(i) + "\t");
		            }
		            System.out.println();
		        }
	        ResultSet rs = stmt.executeQuery("SELECT SUM(altura) AS pesoAgua FROM pokemon AS p JOIN pokemon_tipo AS pt ON p.numero_pokedex = pt.numero_pokedex JOIN tipo AS t ON t.id_tipo = pt.id_tipo AND t.nombre = 'agua' "); 
	        rs.next();
	        float altura = rs.getFloat("pesoAgua");
	       
	        
	        System.out.println("la suma de la altura de todos los pokemon es: "+altura);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
