/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.siga.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	@Size(max = 50)
	@Column(name = "NOMBRE")
	private String nombre;
	@Size(max = 50)
	@Column(name = "APELLIDO")
	private String apellido;
	@Size(max = 30)
	@Column(name = "CORREO_ELECTRONICO")
	private String correoElectronico;
	@Column(name = "NUMERO_TELEFONO_1")
	private Long numeroTelefono1;
	@Column(name = "NUMERO_TELEFONO_2")
	private Long numeroTelefono2;
	@Size(max = 20)
	@Column(name = "USUARIO")
	private String usuario;
	@Size(max = 60)
	@Column(name = "CLAVE")
	private String clave;
	/*
	 * @OneToMany(mappedBy = "userId") private List<Movil> movilList;
	 * 
	 * @OneToMany(mappedBy = "userId") private List<Auditor> auditorList;
	 * 
	 * @OneToMany(mappedBy = "userId") private List<Cliente> clienteList;
	 */
	@JoinColumn(name = "DIRECCION_ID", referencedColumnName = "DIRECCION_ID")
	@ManyToOne
	private Direccion direccionId;

	// roles de usuario
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<UserRole>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Long getNumeroTelefono1() {
		return numeroTelefono1;
	}

	public void setNumeroTelefono1(Long numeroTelefono1) {
		this.numeroTelefono1 = numeroTelefono1;
	}

	public Long getNumeroTelefono2() {
		return numeroTelefono2;
	}

	public void setNumeroTelefono2(Long numeroTelefono2) {
		this.numeroTelefono2 = numeroTelefono2;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Direccion getDireccionId() {
		return direccionId;
	}

	public void setDireccionId(Direccion direccionId) {
		this.direccionId = direccionId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public User(Integer userId, String nombre, String apellido, String correoElectronico, Long numeroTelefono1,
			Long numeroTelefono2, String usuario, String clave, Direccion direccionId, boolean enabled,
			Set<UserRole> userRole) {
		super();
		this.userId = userId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.numeroTelefono1 = numeroTelefono1;
		this.numeroTelefono2 = numeroTelefono2;
		this.usuario = usuario;
		this.clave = clave;
		this.direccionId = direccionId;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	public User(Integer userId, String nombre, String apellido, String correoElectronico, Long numeroTelefono1,
			Long numeroTelefono2, String usuario, String clave, Direccion direccionId, boolean enabled) {
		super();
		this.userId = userId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correoElectronico;
		this.numeroTelefono1 = numeroTelefono1;
		this.numeroTelefono2 = numeroTelefono2;
		this.usuario = usuario;
		this.clave = clave;
		this.direccionId = direccionId;
		this.enabled = enabled;
	}

	public User() {

	}

}
