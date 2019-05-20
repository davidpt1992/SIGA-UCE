package ec.com.siga.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.Seccion;
import ec.com.siga.entity.TipoAuditoria;

@Repository("questRepository")
public interface QuestJpaRepository extends JpaRepository<Preguntas, Serializable>{
	
	public abstract List<Preguntas> findAllByTipoAuditoriaId(TipoAuditoria tipoAuditoriaId);
	public abstract List<Preguntas> findAllByTipoAuditoriaIdAndSeccionId(TipoAuditoria tipoAuditoriaId, Seccion seccion);
	public abstract List<Preguntas> findBySeccionId(Seccion seccionId);
}
