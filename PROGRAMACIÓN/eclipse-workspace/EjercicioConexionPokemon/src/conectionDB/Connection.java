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
		        Statement createStatment = conn.createStatement();
		        ResultSet resultQuery = createStatment.executeQuery("SELECT * FROM pokemon")) {
		        while (resultQuery.next()) {
		            for (int i = 1; i <= 2; i++) {
		                System.out.print(resultQuery.getString(i) + "\t");
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
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			prepStatement.setString(1, nombre);
			prepStatement.setInt(2, numero);
		
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void eliminarPorNumeroBD( int numero) {
    	try {
			conn = crearConexion();
			
			String query = "DELETE FROM pokemon WHERE numero_pokedex = ?";
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			prepStatement.setInt(1, numero);
		
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void escrituraBD(String nombre, int peso, int altura) {
    	try {
			conn = crearConexion();
			Statement createStatement = conn.createStatement();
			ResultSet resultQuery = createStatement.executeQuery("SELECT numero_pokedex FROM pokemon ORDER BY numero_pokedex DESC LIMIT 1");
			
			resultQuery.next();
			int numero_pokedex = resultQuery.getInt("numero_pokedex")+1;
			
			String query = "INSERT INTO pokemon(numero_pokedex, nombre, peso, altura) VALUES (?, ?, ?, ?)";
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			prepStatement.setInt(1, numero_pokedex);
			prepStatement.setString(2, nombre);
			prepStatement.setInt(3, peso);
			prepStatement.setInt(4, altura);
			
			prepStatement.executeUpdate();
			
			System.out.println("El pokemon con numero de pokedex "+numero_pokedex+" ha sido creado con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void leerTipoFuegoBD() {
    	try {
			conn = crearConexion();
			Statement createStatement = conn.createStatement();
	        ResultSet resultQuery = createStatement.executeQuery("SELECT * FROM pokemon as p JOIN pokemon_tipo AS pt ON p.numero_pokedex = pt.numero_pokedex JOIN tipo AS t ON t.id_tipo = pt.id_tipo AND t.nombre = 'fuego'"); 
	        while (resultQuery.next()) {
	            for (int i = 1; i <= 2; i++) {
	                System.out.print(resultQuery.getString(i) + "\t");
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
			Statement createStatement = conn.createStatement();
	        ResultSet resultQuery = createStatement.executeQuery("SELECT AVG(altura) AS media_altura FROM pokemon"); 
	        resultQuery.next();
	        float altura = resultQuery.getFloat("media_altura");
	        
	        System.out.println("la suma de la altura de todos los pokemon es: "+altura);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void sumarPesoAguaBD() {
    	try {
			conn = crearConexion();
			Statement createStatement = conn.createStatement();
			ResultSet resultQueryImp = createStatement.executeQuery("SELECT * FROM pokemon AS p JOIN pokemon_tipo AS pt ON p.numero_pokedex = pt.numero_pokedex JOIN tipo AS t ON t.id_tipo = pt.id_tipo AND t.nombre = 'agua' ");
			 while (resultQueryImp.next()) {
		            for (int i = 1; i <= 2; i++) {
		                System.out.print(resultQueryImp.getString(i) + "\t");
		            }
		            System.out.println();
		        }
	        ResultSet resultQuery = createStatement.executeQuery("SELECT SUM(altura) AS pesoAgua FROM pokemon AS p JOIN pokemon_tipo AS pt ON p.numero_pokedex = pt.numero_pokedex JOIN tipo AS t ON t.id_tipo = pt.id_tipo AND t.nombre = 'agua' "); 
	        resultQuery.next();
	        float altura = resultQuery.getFloat("pesoAgua");
	       
	        
	        System.out.println("la suma de la altura de todos los pokemon es: "+altura);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
