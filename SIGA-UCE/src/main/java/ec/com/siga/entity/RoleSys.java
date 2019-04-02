package ec.com.siga.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role_sys")
public class RoleSys implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "ROLE_ID")
	private Integer roleId;
	@Size(max = 45)
	@Column(name = "ROL")
	private String rol;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public RoleSys(Integer roleId, String rol) {
		super();
		this.roleId = roleId;
		this.rol = rol;

	}

	public RoleSys() {

	}

	@Override
	public String toString() {
		return "RoleSys [roleId=" + roleId + ", rol=" + rol + ", ]";
	}

}
