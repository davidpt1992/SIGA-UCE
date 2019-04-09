package ec.com.siga.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.User;

@Repository("custRepository")
public interface CustRepository extends JpaRepository<Cliente, Serializable>{
	public abstract Cliente findByUserId(User userId);
	
}
