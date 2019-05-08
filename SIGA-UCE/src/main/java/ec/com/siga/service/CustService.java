package ec.com.siga.service;


import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.ClienteTipo;
import ec.com.siga.entity.User;

public interface CustService {
	public abstract void custSave(Cliente cliente);
	public abstract void custUpdate(String usuario, String longitud, String latitud);
	public abstract ClienteTipo findCustTipe(int tipoCLienteId);
	public abstract Cliente findCustById(int clienteId);
	public abstract Cliente findCustByUser(User user);
}
