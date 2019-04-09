package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.RoleSys;
import ec.com.siga.entity.User;

public interface UserServicio {
	public abstract List<User> findAll();
	public abstract User findAdmin(int adminId);
	public abstract User findUserRole(String username);
	public abstract void saveUser(User admin);
	public abstract void deletAdmin(User admin);
	public abstract List<User> findAllAdmin();
	public abstract List<User> findAllBack();
	public abstract List<User> findAllAudi();
	public abstract List<User> findAllCust();
	public abstract List<RoleSys> findAllRole();
	public abstract RoleSys findRoleById(int roleId);
}
