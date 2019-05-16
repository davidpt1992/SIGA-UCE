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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Stalin
 */
@Entity
@Table(name = "tipo_auditoria")
public class TipoAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "TIPO_AUDITORIA_ID")
    private Integer tipoAuditoriaId;
    @Size(max = 40)
    @Column(name = "TIPO_AUDITORIA")
    private String tipoAuditoria;
    @OneToMany(mappedBy = "tipoAuditoriaId", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SolicitudAuditoria> solicitudAuditoriaList;
    @OneToMany(mappedBy = "tipoAuditoriaId")
    @JsonIgnore
    private List<Preguntas> preguntasList;

    public Integer getTipoAuditoriaId() {
		return tipoAuditoriaId;
	}

	public void setTipoAuditoriaId(Integer tipoAuditoriaId) {
		this.tipoAuditoriaId = tipoAuditoriaId;
	}

	public String getTipoAuditoria() {
		return tipoAuditoria;
	}

	public void setTipoAuditoria(String tipoAuditoria) {
		this.tipoAuditoria = tipoAuditoria;
	}

	public List<SolicitudAuditoria> getSolicitudAuditoriaList() {
		return solicitudAuditoriaList;
	}

	public void setSolicitudAuditoriaList(List<SolicitudAuditoria> solicitudAuditoriaList) {
		this.solicitudAuditoriaList = solicitudAuditoriaList;
	}

	public List<Preguntas> getPreguntasList() {
		return preguntasList;
	}

	public void setPreguntasList(List<Preguntas> preguntasList) {
		this.preguntasList = preguntasList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoAuditoria(Integer tipoAuditoriaId, String tipoAuditoria, List<SolicitudAuditoria> solicitudAuditoriaList,
			List<Preguntas> preguntasList) {
		super();
		this.tipoAuditoriaId = tipoAuditoriaId;
		this.tipoAuditoria = tipoAuditoria;
		this.solicitudAuditoriaList = solicitudAuditoriaList;
		this.preguntasList = preguntasList;
	}

	public TipoAuditoria() {
    }

    
}
