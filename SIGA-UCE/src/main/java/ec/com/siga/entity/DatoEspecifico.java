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

@Entity
@Table(name = "dato_especifico")
public class DatoEspecifico implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "DATO_ESPECIFICO_ID")
	private Integer datoEspecificoId;

	@Column(name = "RESPUESTA")
	private boolean respuesta;

	@Size(max = 900)
	@Column(name = "EVIDENCIA")
	private String evidencia;

	@OneToMany(mappedBy = "datoEspecificoId")
	private List<CheckList> checkListList;

	@JoinColumn(name = "FOTO_ID", referencedColumnName = "FOTO_ID")
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Foto fotoId;

	public Integer getDatoEspecificoId() {
		return datoEspecificoId;
	}

	public void setDatoEspecificoId(Integer datoEspecificoId) {
		this.datoEspecificoId = datoEspecificoId;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DatoEspecifico(Integer datoEspecificoId, boolean respuesta, String evidencia,
			List<CheckList> checkListList, Foto fotoId) {
		super();
		this.datoEspecificoId = datoEspecificoId;
		this.respuesta = respuesta;
		this.evidencia = evidencia;
		this.checkListList = checkListList;
		this.fotoId = fotoId;
	}

	public DatoEspecifico() {
	}

}
