package ec.com.siga.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ec.com.siga.entity.Informe;

@Repository("informeRepository")
public interface InformeRepository extends JpaRepository<Informe, Serializable> {

}
