package cl.globewallet.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.globewallet.wallet.model.Transaction;
import cl.globewallet.wallet.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Servicio para gestionar transacciones.
 */
@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BalanceFormatterService balanceFormatterService;

    /**
     * Guarda una transacción en la base de datos.
     *
     * @param transaction la transacción a guardar
     */
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    /**
     * Encuentra todas las transacciones asociadas a un usuario.
     *
     * @param userID el ID del usuario
     * @return una lista de todas las transacciones del usuario
     */
    public List<Transaction> findAllByUser(int userID) {
        List<Transaction> sentTransactions = transactionRepository.findBySenderAccount_User_UserID(userID);
        List<Transaction> receivedTransactions = transactionRepository.findByReceiverAccount_User_UserID(userID);

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(sentTransactions);
        allTransactions.addAll(receivedTransactions);

        allTransactions.forEach(transaction -> {
            String currency = transaction.getTransactionCurrency();
            String formattedAmount = balanceFormatterService.formatWithCurrency(transaction.getTransactionAmount(), currency);
            transaction.setFormattedAmount(formattedAmount);
        });

        allTransactions.sort(Comparator.comparing(Transaction::getTransactionDate).reversed());

        return allTransactions;
    }

    /**
     * Encuentra las transacciones más recientes de un usuario.
     *
     * @param userID el ID del usuario
     * @param limit  el número máximo de transacciones a devolver
     * @return una lista de las transacciones más recientes del usuario
     */
    public List<Transaction> findRecentTransactionsByUser(int userID, int limit) {
        List<Transaction> allTransactions = findAllByUser(userID);
        return allTransactions.stream().limit(limit).toList();
    }
}
