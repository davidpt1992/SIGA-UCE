package ec.com.siga.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.TipoAuditoria;


@Repository("preguntasRepository")
public interface PreguntasRepository extends JpaRepository<Preguntas, Serializable>{
	public abstract List<Preguntas> findByTipoAuditoriaId(TipoAuditoria tipoAuditoriaId);
}
