package cl.globewallet.wallet.controller;

import cl.globewallet.wallet.model.Account;
import cl.globewallet.wallet.model.Transaction;
import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.service.AccountService;
import cl.globewallet.wallet.service.TransactionService;
import cl.globewallet.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para manejar las transferencias de fondos entre cuentas.
 */
@Controller
@RequestMapping("/transfer")
public class TransferController extends BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    /**
     * Muestra la página de transferencias.
     *
     * @param model el modelo para la vista
     * @return el nombre de la plantilla de transferencias
     */
    @GetMapping
    public String showTransferPage(Model model) {
        populateTransferModel(model);
        return "transfer";
    }

    /**
     * Maneja la transferencia de fondos entre cuentas.
     *
     * @param accountId     el ID de la cuenta del remitente
     * @param recipientId   el ID de la cuenta del destinatario
     * @param amount        el monto a transferir
     * @param model         el modelo para la vista
     * @return el nombre de la plantilla de transferencias
     */
    @PostMapping
    public String transferFunds(
            @RequestParam int accountId,
            @RequestParam int recipientId,
            @RequestParam double amount,
            Model model) {
        try {
            if (amount <= 0) {
                model.addAttribute("error", "El monto debe ser mayor a 0.");
                populateTransferModel(model);
                return "transfer";
            }

            Account senderAccount = accountService.findById(accountId)
                    .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
            Account recipientAccount = accountService.findById(recipientId)
                    .orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));

            if (senderAccount.getAccountBalance() < amount) {
                model.addAttribute("error", "Saldo insuficiente");
                populateTransferModel(model);
                return "transfer";
            }

            if (senderAccount.getUser().getUserID() == recipientAccount.getUser().getUserID()) {
                model.addAttribute("error", "No puedes transferir fondos a tu propia cuenta");
                populateTransferModel(model);
                return "transfer";
            }

            senderAccount.setAccountBalance(senderAccount.getAccountBalance() - amount);
            recipientAccount.setAccountBalance(recipientAccount.getAccountBalance() + amount);

            accountService.save(senderAccount);
            accountService.save(recipientAccount);

            Transaction transaction = new Transaction();
            transaction.setSenderAccount(senderAccount);
            transaction.setReceiverAccount(recipientAccount);
            transaction.setTransactionAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setTransactionType("Transfer");
            transaction.setTransactionCurrency(senderAccount.getCurrency().getCurrencyName());

            transactionService.save(transaction);

            model.addAttribute("success", "Transferencia realizada con éxito");
            populateTransferModel(model);
            return "transfer";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            populateTransferModel(model);
            return "transfer";
        }
    }

    /**
     * Llena el modelo con las cuentas del usuario actual y los destinatarios disponibles.
     *
     * @param model el modelo para la vista
     */
    private void populateTransferModel(Model model) {
        User currentUser = getCurrentUser();
        List<Account> accounts = accountService.findAllByUser(currentUser);
        List<User> recipients = userService.findAll().stream()
                .filter(user -> user.getUserID() != currentUser.getUserID())
                .collect(Collectors.toList());

        model.addAttribute("accounts", accounts);
        model.addAttribute("recipients", recipients);
    }

    /**
     * Obtiene las cuentas del destinatario especificado.
     *
     * @param userId el ID del usuario destinatario
     * @return la lista de cuentas del destinatario
     */
    @GetMapping("/api/recipients/{userId}/accounts")
    @ResponseBody
    public List<Account> getRecipientAccounts(@PathVariable int userId) {
        User user = userService.findById(userId).orElse(null);
        return accountService.findAllByUser(user);
    }
}
