package cl.globewallet.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cl.globewallet.wallet.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad User.
 * Proporciona métodos para realizar operaciones CRUD en la tabla Users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Encuentra un usuario por su RUT.
     *
     * @param userRUT el RUT del usuario a buscar
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no se encuentra
     */
    Optional<User> findByUserRUT(String userRUT);

    /**
     * Encuentra todos los usuarios y sus cuentas asociadas.
     * Utiliza una consulta JPQL para hacer una unión izquierda (LEFT JOIN FETCH) con las cuentas de los usuarios.
     *
     * @return una lista de usuarios, cada uno con sus cuentas asociadas
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.accounts")
    List<User> findAllWithAccounts();
}
