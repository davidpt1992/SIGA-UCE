/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.siga.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Stalin
 */
@Entity
@Table(name = "dato_especifico")
public class DatoEspecifico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "DATO_ESPECIFICO_ID")
    private Integer datoEspecificoId;
    @Column(name = "PREGUNTA")
    private Integer pregunta;
    @Size(max = 9)
    @Column(name = "RESPUESTA")
    private String respuesta;
    @Size(max = 500)
    @Column(name = "EVIDENCIA")
    private String evidencia;
    @Size(max = 300)
    @Column(name = "ACCION_RESPUESTA_NEGATIVA")
    private String accionRespuestaNegativa;
    @Column(name = "TIEMPO_RESP_NEGATIVA")
    private Short tiempoRespNegativa;
    @OneToMany(mappedBy = "datoEspecificoId")
    private List<CheckList> checkListList;
    @JoinColumn(name = "FOTO_ID", referencedColumnName = "FOTO_ID")
    @OneToOne(cascade=CascadeType.ALL)
	@JsonIgnore
    private Foto fotoId;

    public Integer getDatoEspecificoId() {
		return datoEspecificoId;
	}

	public void setDatoEspecificoId(Integer datoEspecificoId) {
		this.datoEspecificoId = datoEspecificoId;
	}

	public Integer getPregunta() {
		return pregunta;
	}

	public void setPregunta(Integer pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}

	public String getAccionRespuestaNegativa() {
		return accionRespuestaNegativa;
	}

	public void setAccionRespuestaNegativa(String accionRespuestaNegativa) {
		this.accionRespuestaNegativa = accionRespuestaNegativa;
	}

	public Short getTiempoRespNegativa() {
		return tiempoRespNegativa;
	}

	public void setTiempoRespNegativa(Short tiempoRespNegativa) {
		this.tiempoRespNegativa = tiempoRespNegativa;
	}

	public List<CheckList> getCheckListList() {
		return checkListList;
	}

	public void setCheckListList(List<CheckList> checkListList) {
		this.checkListList = checkListList;
	}

	public Foto getFotoId() {
		return fotoId;
	}

	public void setFotoId(Foto fotoId) {
		this.fotoId = fotoId;
	}

	public DatoEspecifico(Integer datoEspecificoId, Integer pregunta, String respuesta, String evidencia,
			String accionRespuestaNegativa, Short tiempoRespNegativa, List<CheckList> checkListList, Foto fotoId) {
		super();
		this.datoEspecificoId = datoEspecificoId;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.evidencia = evidencia;
		this.accionRespuestaNegativa = accionRespuestaNegativa;
		this.tiempoRespNegativa = tiempoRespNegativa;
		this.checkListList = checkListList;
		this.fotoId = fotoId;
	}

	public DatoEspecifico() {
    }

    
    
}
