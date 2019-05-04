package ec.com.siga.service;


import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.ClienteTipo;

public interface CustService {
	public abstract void custSave(Cliente cliente);
	public abstract ClienteTipo findCustTipe(int tipoCLienteId);
	public abstract Cliente findCustById(int clienteId);
}
