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
@Table(name = "seccion")
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "SECCION_ID")
    private Integer seccionId;
    @Size(max = 45)
    @Column(name = "SECCION")
    private String seccion;
    @OneToMany(mappedBy = "seccionId", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Preguntas> preguntasList;

    public Integer getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(Integer seccionId) {
		this.seccionId = seccionId;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
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

	public Seccion(Integer seccionId, String seccion, List<Preguntas> preguntasList) {
		super();
		this.seccionId = seccionId;
		this.seccion = seccion;
		this.preguntasList = preguntasList;
	}

	public Seccion() {
    }

    
    
}
