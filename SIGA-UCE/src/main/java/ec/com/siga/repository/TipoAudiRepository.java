package ec.com.siga.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.TipoAuditoria;

@Repository("tipoAudiRepository")
public interface TipoAudiRepository extends JpaRepository<TipoAuditoria, Serializable>{
}
