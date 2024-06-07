package cl.globewallet.wallet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.globewallet.wallet.model.Account;
import cl.globewallet.wallet.model.User;

/**
 * Repositorio para la entidad Account.
 * Proporciona métodos para realizar operaciones CRUD en la tabla Accounts.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    /**
     * Encuentra todas las cuentas asociadas a un usuario específico.
     *
     * @param user el usuario cuyas cuentas se desean encontrar
     * @return una lista de cuentas asociadas al usuario proporcionado
     */
    List<Account> findByUser(User user);
    
    /**
     * Encuentra una cuenta por su ID.
     *
     * @param id el ID de la cuenta que se desea encontrar
     * @return una cuenta opcional si se encuentra una cuenta con el ID proporcionado, o una cuenta opcional vacía si no se encuentra ninguna cuenta
     */
    Optional<Account> findById(int id); 
}
