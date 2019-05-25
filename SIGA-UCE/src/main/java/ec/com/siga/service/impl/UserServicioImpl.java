package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.BackOffice;
import ec.com.siga.entity.RoleSys;
import ec.com.siga.entity.User;
import ec.com.siga.repository.BackRepository;
import ec.com.siga.repository.RoleSysJpaRepository;
import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.service.EncryptKey;
import ec.com.siga.service.UserServicio;

@Service("userServicio")
public class UserServicioImpl implements UserServicio {

	@Autowired
	@Qualifier("userRepository")
	private UserJpaRepository userRepository;
	
	@Autowired
	@Qualifier("encryptKey")
	private EncryptKey encriptar;
	
	@Autowired
	@Qualifier("roleSysRepository")
	private RoleSysJpaRepository roleSysRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findAdmin(int adminId) {
		return userRepository.findById(adminId).get();
	}

	@Override
	public void saveUser(User user) {
		user.setClave(encriptar.encryptKey(user.getClave()));
		userRepository.save(user);
				
	}

	@Override
	public void deletAdmin(int adminId) {
		userRepository.deleteById(adminId);
		
	}

	@Override
	public List<User> findAllAdmin() {
		return userRepository.findAllByRoleId(roleSysRepository.findById(4).get());
	}

	@Override
	public List<User> findAllAudi() {
		return userRepository.findAllByRoleId(roleSysRepository.findById(2).get());
	}

	@Override
	public List<User> findAllCust() {
		return userRepository.findAllByRoleId(roleSysRepository.findById(1).get());
	}

	@Override
	public List<RoleSys> findAllRole() {
		return roleSysRepository.findAll();
	}

	@Override
	public User findUserRole(String username) {
		return userRepository.findByUsuario(username);
	}

	@Override
	public RoleSys findRoleById(int roleId) {
		return roleSysRepository.findById(roleId).get();
	}

}
