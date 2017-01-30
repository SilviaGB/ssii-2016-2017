package es.urjc.ssii.ejemplojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?&serverTimezone=UTC", "root", "");
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
			
			crearTabla();
			desconectar();
			
		}
		
		
	}
	

}
