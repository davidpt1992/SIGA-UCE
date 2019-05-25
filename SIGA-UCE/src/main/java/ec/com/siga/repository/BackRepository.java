package ec.com.siga.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.siga.entity.BackOffice;
//import ec.com.siga.entity.User;

@Repository("backRepository")
public interface BackRepository extends JpaRepository<BackOffice, Serializable>{
	//public abstract BackOffice findByUserId(User userId);
	
}
