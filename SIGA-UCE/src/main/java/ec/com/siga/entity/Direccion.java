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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Stalin
 */
@Entity
@Table(name = "direccion")
public class Direccion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "DIRECCION_ID")
	private Integer direccionId;
	@Column(name = "POSTAL_CODIGO")
	private Integer postalCodigo;
	@Size(max = 50)
	@Column(name = "DIRECCION_1")
	private String direccion1;
	@JoinColumn(name = "PARROQUIA_ID", referencedColumnName = "PARROQUIA_ID")
	@ManyToOne
	private Parroquia parroquiaId;
	@JoinColumn(name = "DIRECCION_TIPO_ID", referencedColumnName = "DIRECCION_TIPO_ID")
	@ManyToOne
	private DireccionTipo direccionTipoId;
	/*
	 * @OneToMany(mappedBy = "direccionId") private List<User> userList;
	 */

	public Integer getDireccionId() {
		return direccionId;
	}

	public void setDireccionId(Integer direccionId) {
		this.direccionId = direccionId;
	}

	public Integer getPostalCodigo() {
		return postalCodigo;
	}

	public void setPostalCodigo(Integer postalCodigo) {
		this.postalCodigo = postalCodigo;
	}

	public String getDireccion1() {
		return direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	public Parroquia getParroquiaId() {
		return parroquiaId;
	}

	public void setParroquiaId(Parroquia parroquiaId) {
		this.parroquiaId = parroquiaId;
	}

	public DireccionTipo getDireccionTipoId() {
		return direccionTipoId;
	}

	public void setDireccionTipoId(DireccionTipo direccionTipoId) {
		this.direccionTipoId = direccionTipoId;
	}

	/*
	 * public List<User> getUserList() { return userList; }
	 * 
	 * public void setUserList(List<User> userList) { this.userList = userList;}
	 */
	

	public Direccion(Integer direccionId, Integer postalCodigo, String direccion1, Parroquia parroquiaId,
			DireccionTipo direccionTipoId, List<User> userList) {
		super();
		this.direccionId = direccionId;
		this.postalCodigo = postalCodigo;
		this.direccion1 = direccion1;
		this.parroquiaId = parroquiaId;
		this.direccionTipoId = direccionTipoId;
		//this.userList = userList;
	}

	public Direccion() {

	}

}
