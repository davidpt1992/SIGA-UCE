package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Informe;
import ec.com.siga.repository.CustRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.service.InformeService;

@Service("informeServicio")
public class InformeServiceImpl implements InformeService {

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;
	
	@Autowired
	@Qualifier("userRepository")
	private UserJpaRepository userRepository;
	
	@Autowired
	@Qualifier("custRepository")
	private CustRepository custRepository;

	@Override
	public List<Informe> findAllReport() {
		return informeRepository.findAll();
	}

	@Override
	public Informe findReport(int idReport) {
		return informeRepository.findById(idReport).get();
	}

	@Override
	public void deleteReport(int idReport) {
		informeRepository.deleteById(idReport);

	}

	@Override
	public List<Informe> findAllCustAudits(String username) {
		return informeRepository.findByClienteId(custRepository.findByUserId(userRepository.findByUsuario(username)));
	}

}
