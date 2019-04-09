package ec.com.siga.model;

import java.io.Serializable;

public class SolucitudAuditoriaString implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String auxClienteTipoId;
    public String auxTipoAuditoriaId;
    public String auxfechaInicio;
    public String auxhoraInicio;
    public String auxfechaFinal;
    public String auxhoraFin;
  
	
	public String getAuxClienteTipoId() {
		return auxClienteTipoId;
	}


	public void setAuxClienteTipoId(String auxClienteTipoId) {
		this.auxClienteTipoId = auxClienteTipoId;
	}


	public String getAuxTipoAuditoriaId() {
		return auxTipoAuditoriaId;
	}


	public void setAuxTipoAuditoriaId(String auxTipoAuditoriaId) {
		this.auxTipoAuditoriaId = auxTipoAuditoriaId;
	}


	public String getAuxfechaInicio() {
		return auxfechaInicio;
	}


	public void setAuxfechaInicio(String auxfechaInicio) {
		this.auxfechaInicio = auxfechaInicio;
	}


	public String getAuxhoraInicio() {
		return auxhoraInicio;
	}


	public void setAuxhoraInicio(String auxhoraInicio) {
		this.auxhoraInicio = auxhoraInicio;
	}


	public String getAuxfechaFinal() {
		return auxfechaFinal;
	}


	public void setAuxfechaFinal(String auxfechaFinal) {
		this.auxfechaFinal = auxfechaFinal;
	}


	public String getAuxhoraFin() {
		return auxhoraFin;
	}


	public void setAuxhoraFin(String auxhoraFin) {
		this.auxhoraFin = auxhoraFin;
	}


	public SolucitudAuditoriaString(String auxClienteTipoId, String auxTipoAuditoriaId, String auxfechaInicio,
			String auxhoraInicio, String auxfechaFinal, String auxhoraFin) {
		super();
		this.auxClienteTipoId = auxClienteTipoId;
		this.auxTipoAuditoriaId = auxTipoAuditoriaId;
		this.auxfechaInicio = auxfechaInicio;
		this.auxhoraInicio = auxhoraInicio;
		this.auxfechaFinal = auxfechaFinal;
		this.auxhoraFin = auxhoraFin;
	}


	public SolucitudAuditoriaString() {

	}
  
}