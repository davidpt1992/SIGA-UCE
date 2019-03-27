package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.User;

public interface UserServicio {
	public abstract List<User> findAll();
	public abstract User findAdmin(int adminId);
	public abstract void saveAdmin(User admin);
	public abstract void deletAdmin(User admin);
}
