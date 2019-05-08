package ec.com.siga.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.ClienteTipo;
import ec.com.siga.entity.User;
import ec.com.siga.repository.CustRepository;
import ec.com.siga.repository.TipoCustRepository;
import ec.com.siga.service.CustService;
import ec.com.siga.service.UserServicio;

@Service("custService")
public class CustServiceImpl implements CustService {

	@Autowired
	@Qualifier("custRepository")
	private CustRepository custRepository;
	
	@Autowired
	@Qualifier("tipoCustRepository")
	private TipoCustRepository tipoCustRepository;
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	
	@Override
	public void custSave(Cliente cliente) {
		custRepository.save(cliente);
	}
	
	@Override
	public ClienteTipo findCustTipe(int tipoCLienteId) {
		return tipoCustRepository.findById(tipoCLienteId).get();
	}

	@Override
	public Cliente findCustById(int clienteId) {
		return custRepository.findById(clienteId).get();
	}

	@Override
	public Cliente findCustByUser(User user) {
		return custRepository.findByUserId(user);
	}

	@Override
	public void custUpdate(String usuario, String longitud, String latitud) {
		Cliente cl = findCustByUser(userServicio.findUserRole(usuario));
		cl.setLatitud(latitud);
		cl.setLongitud(longitud);
		custSave(cl);
		
	}
	
}
