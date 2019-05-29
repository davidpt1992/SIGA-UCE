package ec.com.siga.serviceMovil.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.User;
import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.service.EncryptKey;
import ec.com.siga.serviceMovil.AutenticacionServiceMovil;

@Service("autenticacionServiceMovil")
public class AutenticacionServiceMovilImpl implements AutenticacionServiceMovil {

	@Autowired
	@Qualifier("userRepository")
	private UserJpaRepository userRepository;

	@Autowired
	@Qualifier("encryptKey")
	private EncryptKey encryptKey;

	@Override
	public boolean autenticacion(String usuario, String clave) {
		// String claveAux = encryptKey.encryptKey(clave);
		// User userAux=userRepository.findByUsuarioAndClave(usuario, claveAux);

		if (userRepository.findByUsuario(usuario)!= null) {
			return true;
		} else {
			return false;
		}
	}

}
