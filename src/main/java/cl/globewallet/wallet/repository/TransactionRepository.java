package cl.globewallet.wallet.repository;

import cl.globewallet.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Transaction.
 * Proporciona métodos para realizar operaciones CRUD en la tabla Transactions.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    
    /**
     * Encuentra todas las transacciones enviadas por un usuario específico.
     *
     * @param userID el ID del usuario cuyas transacciones enviadas se desean encontrar
     * @return una lista de transacciones enviadas por el usuario con el ID proporcionado
     */
    List<Transaction> findBySenderAccount_User_UserID(int userID);

    /**
     * Encuentra todas las transacciones recibidas por un usuario específico.
     *
     * @param userID el ID del usuario cuyas transacciones recibidas se desean encontrar
     * @return una lista de transacciones recibidas por el usuario con el ID proporcionado
     */
    List<Transaction> findByReceiverAccount_User_UserID(int userID);
}
