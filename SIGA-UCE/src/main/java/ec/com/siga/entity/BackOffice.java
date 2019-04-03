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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "backoffice")
public class BackOffice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "BACKOFFICE_ID")
    private Integer backOfficeId;
    @Size(max = 10)
    @Column(name = "CEDULA")
    private String cedula;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "backOfficeId")
    private List<Informe> informeList;
    
	
	public Integer getBackOfficeId() {
		return backOfficeId;
	}


	public void setBackOfficeId(Integer backOfficeId) {
		this.backOfficeId = backOfficeId;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public List<Informe> getInformeList() {
		return informeList;
	}


	public void setInformeList(List<Informe> informeList) {
		this.informeList = informeList;
	}


	public BackOffice(@NotNull Integer backOfficeId, @Size(max = 10) String cedula, User userId,
			List<Informe> informeList) {
		super();
		this.backOfficeId = backOfficeId;
		this.cedula = cedula;
		this.userId = userId;
		this.informeList = informeList;
	}


	public BackOffice() {
		
	}

    
    
}
