package ec.com.siga.model;

import java.io.Serializable;

public class DireccionString implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String direccionTipoId;
    public String parroquiaId;
    public String postalCode;
    public String address;
  
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDireccionTipoId() {
		return direccionTipoId;
	}
	public void setDireccionTipoId(String direccionTipoId) {
		this.direccionTipoId = direccionTipoId;
	}
	public String getParroquiaId() {
		return parroquiaId;
	}
	public void setParroquiaId(String parroquiaId) {
		this.parroquiaId = parroquiaId;
	}

	public DireccionString(String direccionTipoId, String parroquiaId, String postalCode, String address) {
		super();
		this.direccionTipoId = direccionTipoId;
		this.parroquiaId = parroquiaId;
		this.postalCode = postalCode;
		this.address = address;
	}
	public DireccionString() {

	}
  
}