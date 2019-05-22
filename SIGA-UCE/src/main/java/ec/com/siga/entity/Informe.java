package ec.com.siga.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "informe")
public class Informe implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "INFORME_ID")
	private Integer informeId;

	@JoinColumn(name = "AUDITOR_ID", referencedColumnName = "AUDITOR_ID")
	@ManyToOne
	private Auditor auditorId;

	@JoinColumn(name = "BACKOFFICE_ID", referencedColumnName = "BACKOFFICE_ID")
	@ManyToOne
	private BackOffice backOfficeId;

	@JoinColumn(name = "CLIENTE_ID", referencedColumnName = "CLIENTE_ID")
	@ManyToOne(optional = false)
	private Cliente clienteId;

	@JoinColumn(name = "DATO_COMUN_ID", referencedColumnName = "DATO_COMUN_ID")
	@ManyToOne(optional = false)
	private DatoComun datoComunId;
	
	@JoinColumn(name = "ENTREGABLE_ID", referencedColumnName = "ENTREGABLE_ID")
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Entregable entregableId;

	public Integer getInformeId() {
		return informeId;
	}

	public void setInformeId(Integer informeId) {
		this.informeId = informeId;
	}

	public Auditor getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Auditor auditorId) {
		this.auditorId = auditorId;
	}

	public BackOffice getBackofficeId() {
		return backOfficeId;
	}

	public void setBackofficeId(BackOffice backofficeId) {
		this.backOfficeId = backofficeId;
	}

	public Cliente getClienteId() {
		return clienteId;
	}

	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	public DatoComun getDatoComunId() {
		return datoComunId;
	}

	public void setDatoComunId(DatoComun datoComunId) {
		this.datoComunId = datoComunId;
	}

	public BackOffice getBackOfficeId() {
		return backOfficeId;
	}

	public void setBackOfficeId(BackOffice backOfficeId) {
		this.backOfficeId = backOfficeId;
	}

	public Entregable getEntregableId() {
		return entregableId;
	}

	public void setEntregableId(Entregable entregableId) {
		this.entregableId = entregableId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Informe(Integer informeId, Auditor auditorId, Cliente clienteId, DatoComun datoComunId,
			Entregable entregableId) {
		super();
		this.informeId = informeId;
		this.auditorId = auditorId;
		this.clienteId = clienteId;
		this.datoComunId = datoComunId;
		this.entregableId = entregableId;
	}

	public Informe() {
	}

}
