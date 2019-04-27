package ec.com.siga.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.Auditor;
import ec.com.siga.entity.User;

@Repository("auditorRepository")
public interface AuditorRepository extends JpaRepository<Auditor, Serializable>{
	public abstract Auditor findByUserId(User userId);
	
}
