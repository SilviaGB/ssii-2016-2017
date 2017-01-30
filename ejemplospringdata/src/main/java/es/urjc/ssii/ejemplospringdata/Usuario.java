package es.urjc.ssii.ejemplospringdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/* Al anotar una clase con @Entity se indica a JPA que se trata de un objeto que tendrá
   su tabla equivalente en la base de datos. */

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long iduser;
	
	/* El atributo anotado como @Id será la clave primaria, aquí incremental. */
	
	private String userName, pass;

	// Constructor por defecto, necesario para Spring Data.
	private Usuario() {
	}

	public Usuario(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}

	public String toString() {
		// Convierte el objeto a String para su visualización.
		return this.iduser+":"+this.userName+":"+this.pass;
	}

	
	// Getters y setters
	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
