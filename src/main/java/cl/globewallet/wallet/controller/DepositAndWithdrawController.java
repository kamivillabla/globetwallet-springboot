package cl.globewallet.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import cl.globewallet.wallet.model.Account;
import cl.globewallet.wallet.model.Transaction;
import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.service.AccountService;
import cl.globewallet.wallet.service.TransactionService;
import cl.globewallet.wallet.service.BalanceFormatterService;

import java.util.Date;
import java.util.List;

/**
 * Controlador para manejar las funcionalidades de depósito y retiro.
 */
@Controller
@RequestMapping("/deposit")
public class DepositAndWithdrawController extends BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BalanceFormatterService balanceFormatterService;

    /**
     * Muestra el formulario de transacción de depósito o retiro.
     * 
     * @param selectedAccountId el ID de la cuenta seleccionada (opcional)
     * @param success mensaje de éxito (opcional)
     * @param error mensaje de error (opcional)
     * @param model el modelo para la vista
     * @return el nombre de la plantilla del formulario de transacción
     */
    @GetMapping
    public String showTransactionForm(@RequestParam(value = "selectedAccountId", required = false) Integer selectedAccountId, 
                                      @RequestParam(value = "success", required = false) String success, 
                                      @RequestParam(value = "error", required = false) String error, 
                                      Model model) {
        User currentUser = getCurrentUser();
        List<Account> accounts = accountService.findAllByUser(currentUser);

        for (Account account : accounts) {
            String formattedBalance = balanceFormatterService.format(account.getAccountBalance(), account.getCurrency().getCurrencyName());
            account.setFormattedBalance(formattedBalance);
        }

        Account selectedAccount = selectedAccountId != null
                ? accountService.findById(selectedAccountId).orElse(null)
                : null;

        model.addAttribute("accounts", accounts);
        model.addAttribute("selectedAccount", selectedAccount);
        if (success != null) {
            model.addAttribute("success", success);
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "deposit"; 
    }

    /**
     * Maneja la transacción de depósito o retiro.
     * 
     * @param transactionType el tipo de transacción (deposit o withdraw)
     * @param accountId el ID de la cuenta
     * @param amount la cantidad de dinero a depositar o retirar
     * @param model el modelo para la vista
     * @param redirectAttributes los atributos para redirección
     * @return la redirección al formulario de transacción
     */
    @PostMapping
    public String handleTransaction(@RequestParam("transactionType") String transactionType, 
                                    @RequestParam("accountId") int accountId, 
                                    @RequestParam("amount") double amount, 
                                    Model model, RedirectAttributes redirectAttributes) {
        Account account = accountService.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionCurrency(account.getCurrency().getCurrencyName()); 

        if ("deposit".equalsIgnoreCase(transactionType)) {
            account.setAccountBalance(account.getAccountBalance() + amount);
            transaction.setReceiverAccount(account);
            transaction.setTransactionType("Abono");
            redirectAttributes.addFlashAttribute("success", "Depósito realizado con éxito.");
        } else if ("withdraw".equalsIgnoreCase(transactionType)) {
            if (account.getAccountBalance() < amount) {
                redirectAttributes.addFlashAttribute("error", "Saldo insuficiente.");
                return "redirect:/deposit?selectedAccountId=" + accountId;
            }
            account.setAccountBalance(account.getAccountBalance() - amount);
            transaction.setSenderAccount(account);
            transaction.setTransactionType("Cargo");
            redirectAttributes.addFlashAttribute("success", "Retiro realizado con éxito.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Tipo de transacción inválido.");
            return "redirect:/deposit?selectedAccountId=" + accountId;
        }

        accountService.save(account);
        transactionService.save(transaction);

        return "redirect:/deposit?selectedAccountId=" + accountId;
    }
}
