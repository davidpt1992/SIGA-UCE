package ec.com.siga.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.SolicitudAuditoria;

@Repository("checkListRepository")
public interface CheckListRepository extends JpaRepository<CheckList, Serializable>{
	public abstract List<CheckList> findAllBySolicitudAuditoriaId(SolicitudAuditoria sa);
	public abstract CheckList findByCodigo(int codigo);
	
}
