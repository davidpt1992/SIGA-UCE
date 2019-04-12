/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.siga.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Stalin
 */
@Entity
@Table(name = "estado_auditoria")
public class EstadoAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ESTADO_AUDITORIA_ID")
    private Integer estadoAuditoriaId;
    @Size(max = 20)
    @Column(name = "ESTADO_AUDITORIA")
    private String estadoAuditoria;
    @OneToMany(mappedBy = "estadoAuditoriaId")
    private List<SolicitudAuditoria> solicitudAuditoriaList;
    
    public Integer getEstadoAuditoriaId() {
		return estadoAuditoriaId;
	}

	public void setEstadoAuditoriaId(Integer estadoAuditoriaId) {
		this.estadoAuditoriaId = estadoAuditoriaId;
	}

	public String getEstadoAuditoria() {
		return estadoAuditoria;
	}

	public void setEstadoAuditoria(String estadoAuditoria) {
		this.estadoAuditoria = estadoAuditoria;
	}



	public List<SolicitudAuditoria> getSolicitudAuditoriaList() {
		return solicitudAuditoriaList;
	}

	public void setSolicitudAuditoriaList(List<SolicitudAuditoria> solicitudAuditoriaList) {
		this.solicitudAuditoriaList = solicitudAuditoriaList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EstadoAuditoria(Integer estadoAuditoriaId, String estadoAuditoria,
			List<SolicitudAuditoria> solicitudAuditoriaList) {
		super();
		this.estadoAuditoriaId = estadoAuditoriaId;
		this.estadoAuditoria = estadoAuditoria;
		this.solicitudAuditoriaList = solicitudAuditoriaList;
	}

	public EstadoAuditoria() {
    }

    
}
