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


@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CLIENTE_ID")
    private Integer clienteId;
    @Size(max = 13)
    @Column(name = "RUC")
    private String ruc;
    @Size(max = 30)
    @Column(name = "NOMBRE_EMPRESA")
    private String nombreEmpresa;
    @Size(max = 8)
    @Column(name = "LONGITUD")
    private String longitud;
    @Size(max = 8)
    @Column(name = "LATITUD")
    private String latitud;
    @OneToMany(mappedBy = "clienteId")
    private List<Informe> informeList;
    @JoinColumn(name = "CLIENTE_TIPO_ID", referencedColumnName = "CLIENTE_TIPO_ID")
    @ManyToOne
    private ClienteTipo clienteTipoId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User userId;
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public List<Informe> getInformeList() {
		return informeList;
	}
	public void setInformeList(List<Informe> informeList) {
		this.informeList = informeList;
	}
	public ClienteTipo getClienteTipoId() {
		return clienteTipoId;
	}
	public void setClienteTipoId(ClienteTipo clienteTipoId) {
		this.clienteTipoId = clienteTipoId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Cliente(Integer clienteId, String ruc, String nombreEmpresa, String longitud, String latitud,
			List<Informe> informeList, ClienteTipo clienteTipoId, User userId) {
		super();
		this.clienteId = clienteId;
		this.ruc = ruc;
		this.nombreEmpresa = nombreEmpresa;
		this.longitud = longitud;
		this.latitud = latitud;
		this.informeList = informeList;
		this.clienteTipoId = clienteTipoId;
		this.userId = userId;
	}
	public Cliente() {
		
	}

    
    
}
