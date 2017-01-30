package es.urjc.ssii.ejemplospringdata;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioUsuario extends CrudRepository<Usuario,Long> {

	List<Usuario> findByUserName(String userName);
	
	@Query(value = "SELECT * FROM USUARIO", nativeQuery = true)
	List<Usuario> todos();
}
