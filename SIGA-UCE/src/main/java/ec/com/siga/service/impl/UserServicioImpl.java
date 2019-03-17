package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.User;

import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.service.UserServicio;

@Service("userService")
public class UserServicioImpl implements UserServicio {

	@Autowired
	@Qualifier("userRepository")
	private UserJpaRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
