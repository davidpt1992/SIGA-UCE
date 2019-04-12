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


/**
 *
 * @author Stalin
 */
@Entity
@Table(name = "solicitud_auditoria")
public class SolicitudAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "SOLICITUD_AUDITORIA_ID")
    private Integer solicitudAuditoriaId;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @JoinColumn(name = "ESTADO_AUDITORIA_ID", referencedColumnName = "ESTADO_AUDITORIA_ID")
    @ManyToOne
    private EstadoAuditoria estadoAuditoriaId;
    @OneToMany(mappedBy = "solicitudAuditoriaId")
    private List<CheckList> checkListList;
    @OneToMany(mappedBy = "solicitudAuditoriaId")
    private List<DatoComun> datoComunList;
    @JoinColumn(name = "TIPO_AUDITORIA_ID", referencedColumnName = "TIPO_AUDITORIA_ID")
    @ManyToOne
    private TipoAuditoria tipoAuditoriaId;

	
    public Integer getSolicitudAuditoriaId() {
		return solicitudAuditoriaId;
	}


	public void setSolicitudAuditoriaId(Integer solicitudAuditoriaId) {
		this.solicitudAuditoriaId = solicitudAuditoriaId;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}


	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}


	public EstadoAuditoria getEstadoAuditoriaId() {
		return estadoAuditoriaId;
	}


	public void setEstadoAuditoriaId(EstadoAuditoria estadoAuditoriaId) {
		this.estadoAuditoriaId = estadoAuditoriaId;
	}


	public List<CheckList> getCheckListList() {
		return checkListList;
	}


	public void setCheckListList(List<CheckList> checkListList) {
		this.checkListList = checkListList;
	}


	public List<DatoComun> getDatoComunList() {
		return datoComunList;
	}


	public void setDatoComunList(List<DatoComun> datoComunList) {
		this.datoComunList = datoComunList;
	}


	public TipoAuditoria getTipoAuditoriaId() {
		return tipoAuditoriaId;
	}


	public void setTipoAuditoriaId(TipoAuditoria tipoAuditoriaId) {
		this.tipoAuditoriaId = tipoAuditoriaId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SolicitudAuditoria(Integer solicitudAuditoriaId, Date fechaInicio, Date fechaFinal,
			EstadoAuditoria estadoAuditoriaId, List<CheckList> checkListList, List<DatoComun> datoComunList,
			TipoAuditoria tipoAuditoriaId) {
		super();
		this.solicitudAuditoriaId = solicitudAuditoriaId;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.estadoAuditoriaId = estadoAuditoriaId;
		this.checkListList = checkListList;
		this.datoComunList = datoComunList;
		this.tipoAuditoriaId = tipoAuditoriaId;
	}

	public SolicitudAuditoria() {
    }

    
    
}
