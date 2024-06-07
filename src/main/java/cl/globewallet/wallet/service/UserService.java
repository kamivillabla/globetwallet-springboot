package cl.globewallet.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar usuarios.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param user el usuario a guardar
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Busca un usuario por su RUT.
     *
     * @param userRUT el RUT del usuario
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no
     */
    public Optional<User> findByUserRUT(String userRUT) {
        return userRepository.findByUserRUT(userRUT);
    }

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return una lista de todos los usuarios
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param userId el ID del usuario
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no
     */
    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }
}
