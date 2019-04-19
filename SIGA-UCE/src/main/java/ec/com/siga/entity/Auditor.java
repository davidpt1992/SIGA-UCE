/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.siga.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "auditor")
public class Auditor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "AUDITOR_ID")
    private Integer auditorId;
    @Column(name = "IMEI")
    private BigInteger imei;
    @Size(max = 25)
    @Column(name = "TOKEN_UNIQUE")
    private String tokenUnique;
    @OneToMany(mappedBy = "auditorId")
    private List<Movil> movilList;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private User userId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditorId")
    private List<Informe> informeList;
    
    
    @OneToMany(mappedBy = "auditorId")
    private List<DeviceToken> deviceTokenList;
	public Integer getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}
	public BigInteger getImei() {
		return imei;
	}
	public void setImei(BigInteger imei) {
		this.imei = imei;
	}
	public String getTokenUnique() {
		return tokenUnique;
	}
	public void setTokenUnique(String tokenUnique) {
		this.tokenUnique = tokenUnique;
	}
	public List<Movil> getMovilList() {
		return movilList;
	}
	public void setMovilList(List<Movil> movilList) {
		this.movilList = movilList;
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
	public List<DeviceToken> getDeviceTokenList() {
		return deviceTokenList;
	}
	public void setDeviceTokenList(List<DeviceToken> deviceTokenList) {
		this.deviceTokenList = deviceTokenList;
	}
	public Auditor(Integer auditorId, BigInteger imei, String tokenUnique, List<Movil> movilList, User userId,
			List<Informe> informeList, List<DeviceToken> deviceTokenList) {
		super();
		this.auditorId = auditorId;
		this.imei = imei;
		this.tokenUnique = tokenUnique;
		this.movilList = movilList;
		this.userId = userId;
		this.informeList = informeList;
		this.deviceTokenList = deviceTokenList;
	}
	public Auditor() {
		
	}

    
    
}
