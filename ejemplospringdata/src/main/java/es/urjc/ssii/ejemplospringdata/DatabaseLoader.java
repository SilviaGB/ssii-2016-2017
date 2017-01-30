package es.urjc.ssii.ejemplospringdata;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

	@Autowired
	private RepositorioUsuario repositorio;
	
	@PostConstruct
	private void initDatabase() {
		
		// Nuevos usuarios
		repositorio.save(new Usuario("usuario1", "1234"));
		repositorio.save(new Usuario("usuario2", "4567"));
		
		// Update
		Usuario usu = repositorio.findAll().iterator().next();
		System.out.println("\nUsuario a cambiar: "+usu);
		usu.setUserName("usuario111");
		repositorio.save(usu);
		
		// Lectura de datos
		System.out.println("\nListado de usuarios");
		Iterable<Usuario> all = repositorio.findAll();
		for (Usuario u : all) {
			System.out.println(u);
		}
		
		// Borrado
		long firstId = repositorio.findAll().iterator().next().getIduser();
		repositorio.delete(firstId);
		System.out.println("\nFilas que quedan: "+repositorio.count()+"\n");
	}
	
	
}
