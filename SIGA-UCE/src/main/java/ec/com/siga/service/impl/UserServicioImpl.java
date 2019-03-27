package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.User;

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

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findAdmin(int adminId) {
		return userRepository.findById(adminId).get();
	}

	@Override
	public void saveAdmin(User admin) {
		admin.setClave(encriptar.encryptKey(admin.getClave()));
		userRepository.save(admin);
				
	}

	@Override
	public void deletAdmin(User admin) {
		userRepository.delete(admin);
		
	}

}
