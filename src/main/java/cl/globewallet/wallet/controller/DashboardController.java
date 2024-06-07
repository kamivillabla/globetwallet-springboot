package cl.globewallet.wallet.controller;

import cl.globewallet.wallet.model.Account;
import cl.globewallet.wallet.model.Currency;
import cl.globewallet.wallet.model.Transaction;
import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.service.AccountService;
import cl.globewallet.wallet.service.BalanceFormatterService;
import cl.globewallet.wallet.service.TransactionService;
import cl.globewallet.wallet.service.CurrencyService;
import cl.globewallet.wallet.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

/**
 * Controlador para manejar las funcionalidades del tablero de control del usuario.
 */
@Controller
public class DashboardController extends BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BalanceFormatterService balanceFormatterService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * Muestra el tablero de control del usuario.
     * 
     * @param model el modelo para la vista
     * @return el nombre de la plantilla del tablero de control
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            List<Account> accounts = accountService.findAllByUser(currentUser);
            for (Account account : accounts) {
                String formattedBalance = balanceFormatterService.format(account.getAccountBalance(), account.getCurrency().getCurrencyName());
                account.setFormattedBalance(formattedBalance);
            }
            model.addAttribute("accounts", accounts);

            // Obtener las últimas tres transacciones
            List<Transaction> recentTransactions = transactionService.findRecentTransactionsByUser(currentUser.getUserID(), 3);
            model.addAttribute("recentTransactions", recentTransactions);
        } else {
            model.addAttribute("recentTransactions", List.of());
        }
        model.addAttribute("account", new Account());
        model.addAttribute("accountTypes", getDefaultAccountTypes());
        return "dashboard";
    }

    /**
     * Convierte el saldo de una cuenta entre CLP y USD.
     * 
     * @param accountId el ID de la cuenta a convertir
     * @param model el modelo para la vista
     * @return la redirección al tablero de control
     */
    @PostMapping("/accounts/convert")
    public String convertBalance(@RequestParam("accountId") int accountId, Model model) {
        Account account = accountService.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        double exchangeRate = exchangeRateService.getDollarExchangeRate();

        if ("CLP".equals(account.getCurrency().getCurrencyName())) {
            double usdBalance = account.getAccountBalance() / exchangeRate;
            Currency usdCurrency = currencyService.ensureCurrencyExists("USD", "$", 1.0f);
            account.setAccountBalance(usdBalance);
            account.setCurrency(usdCurrency);
        } else if ("USD".equals(account.getCurrency().getCurrencyName())) {
            double clpBalance = account.getAccountBalance() * exchangeRate;
            Currency clpCurrency = currencyService.ensureCurrencyExists("CLP", "$", 1.0f);
            account.setAccountBalance(clpBalance);
            account.setCurrency(clpCurrency);
        }

        accountService.save(account);
        return "redirect:/dashboard";
    }

    /**
     * Agrega una nueva cuenta para el usuario actual.
     * 
     * @param account el objeto de la cuenta a agregar
     * @param bankName el nombre del banco
     * @param accountType el tipo de cuenta
     * @return la redirección al tablero de control
     */
    @PostMapping("/accounts/add")
    public String addAccount(@ModelAttribute("account") Account account,
                             @ModelAttribute("bankName") String bankName,
                             @ModelAttribute("accountType") String accountType) {

        // Establecer moneda predeterminada (CLP)
        Currency clpCurrency = currencyService.ensureCurrencyExists("CLP", "$", 1.0f);
        account.setCurrency(clpCurrency);

        // Generar un saldo aleatorio entre 0 y 20,000 CLP
        Random random = new Random();
        double randomBalance = random.nextInt(20001);
        account.setAccountBalance(randomBalance);

        // Establecer tipo de cuenta y banco
        account.setAccountType(accountType);
        account.setBankName(bankName);

        // Obtener usuario actual y asignarlo a la cuenta
        User user = getCurrentUser();
        if (user != null) {
            account.setUser(user);
            accountService.save(account);
        }

        return "redirect:/dashboard";
    }

    /**
     * Obtiene los tipos de cuenta predeterminados.
     * 
     * @return una lista de tipos de cuenta predeterminados
     */
    private List<String> getDefaultAccountTypes() {
        return List.of("Cuenta Corriente", "Cuenta Vista");
    }
}
