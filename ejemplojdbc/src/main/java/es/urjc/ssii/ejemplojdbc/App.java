package es.urjc.ssii.ejemplojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	static Connection conn;
	
	/**
	 * Establece la conexión con una base de datos MySQL existente de nombre test.
	 * Si no existe, se puede crear con el comando SQL: CREATE DATABASE test;
	 * Modifica las credenciales de root si tienes otra configuración.
	 */
	public static boolean conectar() {
		boolean connected = false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "");
			connected = true;
		} catch (SQLException e) {
			System.err.println("Error en la conexión: "+e.getLocalizedMessage());
		}
		
		return connected;
	}
	
	
	/**
	 * Creación de una tabla en la base de datos.
	 */
	public static void crearTabla() {
		String createSql = "CREATE TABLE usuarios (iduser INT NOT NULL AUTO_INCREMENT, "
				+ "username VARCHAR(45) NOT NULL UNIQUE, pass VARCHAR(50) NOT NULL, " + "PRIMARY KEY (iduser))";
		
		Statement statement;

		try {
			statement = conn.createStatement();
			statement.execute(createSql);
			statement.close();
		} catch (SQLException e) {
			System.err.println("Error en la creación de la tabla: "+e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * Inserción de algunas filas en la tabla.
	 */
	public static void insertarDatos() {
		String insertSql = "INSERT INTO usuarios (username,pass) VALUES ('usuario1','1234'),('usuario2','4567')";
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(insertSql);
			statement.close();
		} catch (SQLException e) {
			System.err.println("Error en la inserción de datos: "+e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * Muestra los datos de la tabla.
	 */
	public static void mostrarDatos() {
		String selectSql = "SELECT * FROM usuarios";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(selectSql);
			while (rs.next()) {
				System.out.println(rs.getInt("iduser") + ":" + rs.getString("username") + ":" + rs.getString("pass"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			System.err.println("Error al mostrar los datos de la tabla: " + e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * Modifica los datos de la tabla poniendo la misma contraseña a todos.
	 */
	public static void modificarDatos() {
		String updateSql = "UPDATE usuarios SET pass='55555'";
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(updateSql);
			statement.close();
		} catch (SQLException e) {
			System.err.println("Error al modificar los datos de la tabla: " + e.getLocalizedMessage());
		} 
	}
	
	
	/**
	 * Borra los datos de la tabla, pero no borra la propia estructura de tabla.
	 */
	public static void borrarDatos() {
		String deleteSql = "DELETE FROM usuarios";
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(deleteSql);
			statement.close();
		} catch (SQLException e) {
			System.err.println("Error al borrar los datos de la tabla: " + e.getLocalizedMessage());
		} 
	}
	
	
	/**
	 * Desconexión de la base de datos.
	 */
	public static void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error en la desconexión: "+e.getLocalizedMessage());
		}
	}
	
	
	public static void main(String[] args) {
		
		if (conectar()) {
			
			System.out.println("\nCreación de tabla");
			crearTabla();
			System.out.println("\nInserción de datos");
			insertarDatos();
			System.out.println("\nContenido de la tabla");
			mostrarDatos();
			System.out.println("\nModificación de datos");
			modificarDatos();
			System.out.println("\nContenido de la tabla");
			mostrarDatos();
			System.out.println("\nBorrado de datos");
			borrarDatos();
			desconectar();
			
		}
		
		
	}
	

}
