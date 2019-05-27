package ec.com.siga.model;

import java.io.Serializable;

public class AuditDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public String id_informe;
	public String id_cliente;
	public String latitud;
	public String longitud;
	public String empresa;
	public String nombreApellido;
	public String correo;
	public String direccion;
	public String telf1;
	public String telf2;
	public String fechaIni;
	public String fechaFin;
	public String horaIni;
	public String horaFin;
	public String id_estado;
	public String id_solicitud;
  
	public String getId_informe() {
		return id_informe;
	}

	public void setId_informe(String id_informe) {
		this.id_informe = id_informe;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelf1() {
		return telf1;
	}

	public void setTelf1(String telf1) {
		this.telf1 = telf1;
	}

	public String getTelf2() {
		return telf2;
	}

	public void setTelf2(String telf2) {
		this.telf2 = telf2;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getId_estado() {
		return id_estado;
	}

	public void setId_estado(String id_estado) {
		this.id_estado = id_estado;
	}

	public String getId_solicitud() {
		return id_solicitud;
	}

	public void setId_solicitud(String id_solicitud) {
		this.id_solicitud = id_solicitud;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AuditDTO(String id_informe, String id_cliente, String latitud, String longitud, String empresa,
			String nombreApellido, String correo, String direccion, String telf1, String telf2, String fechaIni,
			String fechaFin, String horaIni, String horaFin, String id_estado, String id_solicitud) {
		super();
		this.id_informe = id_informe;
		this.id_cliente = id_cliente;
		this.latitud = latitud;
		this.longitud = longitud;
		this.empresa = empresa;
		this.nombreApellido = nombreApellido;
		this.correo = correo;
		this.direccion = direccion;
		this.telf1 = telf1;
		this.telf2 = telf2;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.id_estado = id_estado;
		this.id_solicitud = id_solicitud;
	}

	public AuditDTO() {

	}
  
}