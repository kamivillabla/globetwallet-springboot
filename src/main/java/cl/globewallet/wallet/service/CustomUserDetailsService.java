package cl.globewallet.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.globewallet.wallet.repository.UserRepository;

import java.util.Collections;

/**
 * Servicio personalizado para la autenticación de usuarios.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Carga los detalles del usuario por su nombre de usuario (RUT).
     *
     * @param username el nombre de usuario (RUT) del usuario
     * @return los detalles del usuario
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        cl.globewallet.wallet.model.User user = userRepository.findByUserRUT(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with RUT : " + username));

        return new User(user.getUserRUT(), user.getUserPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
