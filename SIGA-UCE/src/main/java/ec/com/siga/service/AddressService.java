package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.Canton;
import ec.com.siga.entity.Direccion;
import ec.com.siga.entity.DireccionTipo;
import ec.com.siga.entity.Pais;
import ec.com.siga.entity.Parroquia;
import ec.com.siga.entity.ProvinciaEstado;
import ec.com.siga.model.DireccionString;

public interface AddressService {

	public abstract List<ProvinciaEstado> findAllByPaisId(int paisId);
	public abstract List<Canton> findAllByPrpvinciaId(int provinciaId);
	public abstract List<Parroquia> findAllByCantonId(int cantonId);
	public abstract Direccion saveDireccion(Direccion direccion);
	public abstract DireccionTipo findDireccionById(int direccionId);
	public abstract List<DireccionTipo> findAllDireccionTipo();
	
	public abstract Parroquia findParroquiaById(int parroquiaId);
	
	public abstract List<Pais> findAllPais();
	
	public abstract Direccion converterDireccionStringToDirection(DireccionString direccionString); //convierte direccion string a direccionentidad

}
