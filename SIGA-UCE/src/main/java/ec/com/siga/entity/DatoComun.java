/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.siga.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Stalin
 */
@Entity
@Table(name = "dato_comun")
public class DatoComun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "DATO_COMUN_ID")
    private Integer datoComunId;
    @Column(name = "HORA_INICIO")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "HORA_FIN")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Size(max = 8)
    @Column(name = "LATITUD")
    private String latitud;
    @Size(max = 8)
    @Column(name = "LONGITUD")
    private String longitud;
    @Column(name = "CALIFICACION")
    private Integer calificacion;
    @Column(name = "CALIFICACION_FINAL")
    private Integer calificacionFinal;
    @OneToMany(mappedBy = "datoComunId")
    private List<Informe> informeList;
    @JoinColumn(name = "SOLICITUD_AUDITORIA_ID", referencedColumnName = "SOLICITUD_AUDITORIA_ID")
    @ManyToOne
    private SolicitudAuditoria solicitudAuditoriaId;

    public Integer getDatoComunId() {
		return datoComunId;
	}

	public void setDatoComunId(Integer datoComunId) {
		this.datoComunId = datoComunId;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
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

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Integer getCalificacionFinal() {
		return calificacionFinal;
	}

	public void setCalificacionFinal(Integer calificacionFinal) {
		this.calificacionFinal = calificacionFinal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Informe> getInformeList() {
		return informeList;
	}

	public void setInformeList(List<Informe> informeList) {
		this.informeList = informeList;
	}

	public SolicitudAuditoria getSolicitudAuditoriaId() {
		return solicitudAuditoriaId;
	}

	public void setSolicitudAuditoriaId(SolicitudAuditoria solicitudAuditoriaId) {
		this.solicitudAuditoriaId = solicitudAuditoriaId;
	}

	public DatoComun(Integer datoComunId, Date horaInicio, Date horaFin, String latitud,
			String longitud, Integer calificacion, Integer calificacionFinal, List<Informe> informeList,
			SolicitudAuditoria solicitudAuditoriaId) {
		super();
		this.datoComunId = datoComunId;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.latitud = latitud;
		this.longitud = longitud;
		this.calificacion = calificacion;
		this.calificacionFinal = calificacionFinal;
		this.informeList = informeList;
		this.solicitudAuditoriaId = solicitudAuditoriaId;
	}

	public DatoComun() {
    }

    
}
