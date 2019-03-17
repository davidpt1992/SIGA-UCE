package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Canton;
import ec.com.siga.entity.Direccion;
import ec.com.siga.entity.DireccionTipo;
import ec.com.siga.entity.ProvinciaEstado;
import ec.com.siga.model.DireccionString;
import ec.com.siga.entity.Pais;
import ec.com.siga.entity.Parroquia;
import ec.com.siga.repository.CantonJpaRepository;
import ec.com.siga.repository.DireccionRepository;
import ec.com.siga.repository.DireccionTipoJpaRepository;
import ec.com.siga.repository.PaisJpaRepository;
import ec.com.siga.repository.ParroquiaJpaRepository;
import ec.com.siga.repository.ProvinciaJpaRepository;
import ec.com.siga.service.AddressService;

@Service("addressService")
public class AddressServiseImpl implements AddressService{

	@Autowired
	@Qualifier("paisRepository")
	private PaisJpaRepository paisRepository;
	
	@Autowired
	@Qualifier("provinciaRepository")
	private ProvinciaJpaRepository provinciaRepository;
	
	@Autowired
	@Qualifier("cantonRepository")
	private CantonJpaRepository cantonRepository;
	
	@Autowired
	@Qualifier("parroquiaRepository")
	private ParroquiaJpaRepository parroquiaRepository;
	
	@Autowired
	@Qualifier("direccionRepository")
	private DireccionRepository direccionRepository;
	
	@Autowired
	@Qualifier("direccionTipoRepository")
	private DireccionTipoJpaRepository direccionTipoRepository;
	
	@Override
	public Direccion saveDireccion(Direccion direccion) {
		direccionRepository.save(direccion);
		return null;
	}

	
	@Override
	public List<ProvinciaEstado> findAllByPaisId(int paisId) {
		return provinciaRepository.findAllByPaisId(paisRepository.findById(paisId).get());
	}

	
	@Override
	public List<DireccionTipo> findAllDireccionTipo() {
		return direccionTipoRepository.findAll();
	}


	@Override
	public List<Pais> findAllPais() {
		return paisRepository.findAll();
	}


	@Override
	public List<Canton> findAllByPrpvinciaId(int provinciaId) {
		return cantonRepository.findAllByProvinciaId(provinciaRepository.findById(provinciaId).get());
	}


	@Override
	public List<Parroquia> findAllByCantonId(int cantonId) {
		return parroquiaRepository.findAllByCantonId(cantonRepository.findById(cantonId).get());
	}


	@Override
	public DireccionTipo findDireccionById(int direccionId) {
		return direccionTipoRepository.findById(direccionId).get();
	}


	@Override
	public Parroquia findParroquiaById(int parroquiaId) {
		return parroquiaRepository.findById(parroquiaId).get();
	}


	@Override
	public Direccion converterDireccionStringToDirection(DireccionString direccionString) {
		Direccion dir=new Direccion();
		dir.setDireccion1(direccionString.getAddress());
		dir.setPostalCodigo(Integer.valueOf(direccionString.getPostalCode()));
		dir.setDireccionTipoId(direccionTipoRepository.findById(Integer.valueOf(direccionString.getDireccionTipoId())).get());
		dir.setParroquiaId(parroquiaRepository.findById(Integer.valueOf(direccionString.getParroquiaId())).get());
		return dir;
	}
	




}
