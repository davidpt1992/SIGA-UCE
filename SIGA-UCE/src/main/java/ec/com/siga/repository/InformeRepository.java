package ec.com.siga.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.Informe;

@Repository("informeRepository")
public interface InformeRepository extends JpaRepository<Informe, Serializable> {
	
	public abstract List<Informe> findByClienteId(Cliente clienteId);

}
