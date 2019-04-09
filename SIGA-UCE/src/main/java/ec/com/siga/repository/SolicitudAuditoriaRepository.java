package ec.com.siga.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.SolicitudAuditoria;

@Repository("solicitudAuditoriaRepository")
public interface SolicitudAuditoriaRepository extends JpaRepository<SolicitudAuditoria, Serializable>{
	//public abstract Cliente findByUserId(User userId);
	
}
