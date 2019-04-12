package ec.com.siga.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.DatoComun;

@Repository("dComunRepository")
public interface DatoComunRepository extends JpaRepository<DatoComun, Serializable> {
}
