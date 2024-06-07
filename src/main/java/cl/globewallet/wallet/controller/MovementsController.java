package cl.globewallet.wallet.controller;

import cl.globewallet.wallet.model.Transaction;
import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controlador para manejar la visualización de movimientos de transacciones.
 */
@Controller
@RequestMapping("/movements")
public class MovementsController extends BaseController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Muestra los movimientos de transacciones del usuario actual.
     * 
     * @param model el modelo para la vista
     * @return el nombre de la plantilla de movimientos
     */
    @GetMapping
    public String showMovements(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            List<Transaction> transactions = transactionService.findAllByUser(currentUser.getUserID());
            model.addAttribute("transactions", transactions);
            model.addAttribute("currentUser", currentUser);
        }
        return "movements";
    }
}
