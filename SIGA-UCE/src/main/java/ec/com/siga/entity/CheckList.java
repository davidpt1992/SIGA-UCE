/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.siga.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "check_list")
public class CheckList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CHECK_LIST_ID")
    private Integer checkListId;
    @JoinColumn(name = "PREGUNTAS_ID", referencedColumnName = "PREGUNTAS_ID")
    @ManyToOne
    private Preguntas preguntasId;
    @Column(name = "CODIGO")
	private Integer codigo;
    @JoinColumn(name = "DATO_ESPECIFICO_ID", referencedColumnName = "DATO_ESPECIFICO_ID")
    @ManyToOne
    private DatoEspecifico datoEspecificoId;
    @JoinColumn(name = "SOLICITUD_AUDITORIA_ID", referencedColumnName = "SOLICITUD_AUDITORIA_ID")
    @ManyToOne
    private SolicitudAuditoria solicitudAuditoriaId;
	public Integer getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(Integer checkListId) {
		this.checkListId = checkListId;
	}
	public Preguntas getPreguntasId() {
		return preguntasId;
	}
	public void setPreguntasId(Preguntas preguntasId) {
		this.preguntasId = preguntasId;
	}
	public DatoEspecifico getDatoEspecificoId() {
		return datoEspecificoId;
	}
	public void setDatoEspecificoId(DatoEspecifico datoEspecificoId) {
		this.datoEspecificoId = datoEspecificoId;
	}
	public SolicitudAuditoria getSolicitudAuditoriaId() {
		return solicitudAuditoriaId;
	}
	public void setSolicitudAuditoriaId(SolicitudAuditoria solicitudAuditoriaId) {
		this.solicitudAuditoriaId = solicitudAuditoriaId;
	}
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public CheckList(Integer checkListId, Preguntas preguntasId, Integer codigo,
			DatoEspecifico datoEspecificoId, SolicitudAuditoria solicitudAuditoriaId) {
		super();
		this.checkListId = checkListId;
		this.preguntasId = preguntasId;
		this.codigo = codigo;
		this.datoEspecificoId = datoEspecificoId;
		this.solicitudAuditoriaId = solicitudAuditoriaId;
	}

	public CheckList() {
		
	}
    
}
