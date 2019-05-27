package ec.com.siga.model;

import java.io.Serializable;

public class CredsDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public String username;
	public String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CredsDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public CredsDTO() {

	}
  
}