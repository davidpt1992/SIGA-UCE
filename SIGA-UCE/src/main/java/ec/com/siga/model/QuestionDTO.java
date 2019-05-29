package ec.com.siga.model;

import java.io.Serializable;

public class QuestionDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public String id_checklist;
	public String codigo;
	public String id_solicitud;
	public String id_pregunta;
	public String pregunta;
	public String id_despecifico;
	public String evidencia;
	public String respuesta;
	public String id_foto;
	public String foto;
  
	public String getId_checklist() {
		return id_checklist;
	}

	public void setId_checklist(String id_checklist) {
		this.id_checklist = id_checklist;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getId_solicitud() {
		return id_solicitud;
	}

	public void setId_solicitud(String id_solicitud) {
		this.id_solicitud = id_solicitud;
	}

	public String getId_pregunta() {
		return id_pregunta;
	}

	public void setId_pregunta(String id_pregunta) {
		this.id_pregunta = id_pregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getId_despecifico() {
		return id_despecifico;
	}

	public void setId_despecifico(String id_despecifico) {
		this.id_despecifico = id_despecifico;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getId_foto() {
		return id_foto;
	}

	public void setId_foto(String id_foto) {
		this.id_foto = id_foto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public QuestionDTO(String id_checklist, String codigo, String id_solicitud, String id_pregunta, String pregunta,
			String id_despecifico, String evidencia, String respuesta, String id_foto, String foto) {
		super();
		this.id_checklist = id_checklist;
		this.codigo = codigo;
		this.id_solicitud = id_solicitud;
		this.id_pregunta = id_pregunta;
		this.pregunta = pregunta;
		this.id_despecifico = id_despecifico;
		this.evidencia = evidencia;
		this.respuesta = respuesta;
		this.id_foto = id_foto;
		this.foto = foto;
	}

	public QuestionDTO() {

	}
  
}