package cl.globewallet.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.globewallet.wallet.model.Account;
import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las operaciones relacionadas con las cuentas de usuario.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Guarda una cuenta en el repositorio.
     *
     * @param account la cuenta a guardar
     */
    public void save(Account account) {
        accountRepository.save(account);
    }

    /**
     * Encuentra todas las cuentas asociadas a un usuario.
     *
     * @param user el usuario cuyas cuentas se buscan
     * @return una lista de cuentas asociadas al usuario
     */
    public List<Account> findAllByUser(User user) {
        return accountRepository.findByUser(user);
    }

    /**
     * Encuentra una cuenta por su ID.
     *
     * @param accountId el ID de la cuenta a buscar
     * @return un Optional que contiene la cuenta si se encuentra, o vacío si no se encuentra
     */
    public Optional<Account> findById(int accountId) {
        return accountRepository.findById(accountId);
    }

    /**
     * Deposita una cantidad de dinero en una cuenta.
     *
     * @param accountId el ID de la cuenta en la que se va a depositar
     * @param amount la cantidad a depositar
     * @throws IllegalArgumentException si la cuenta no se encuentra
     */
    public void deposit(int accountId, double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setAccountBalance(account.getAccountBalance() + amount);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Cuenta no encontrada para ID: " + accountId);
        }
    }

    /**
     * Retira una cantidad de dinero de una cuenta.
     *
     * @param accountId el ID de la cuenta de la que se va a retirar
     * @param amount la cantidad a retirar
     * @throws IllegalArgumentException si la cuenta no se encuentra o si el saldo es insuficiente
     */
    public void withdraw(int accountId, double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getAccountBalance() >= amount) {
                account.setAccountBalance(account.getAccountBalance() - amount);
                accountRepository.save(account);
            } else {
                throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro.");
            }
        } else {
            throw new IllegalArgumentException("Cuenta no encontrada para ID: " + accountId);
        }
    }
}
