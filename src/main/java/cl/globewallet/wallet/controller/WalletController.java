package cl.globewallet.wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las rutas principales de la aplicación.
 */
@Controller
public class WalletController {

    /**
     * Muestra la página de inicio.
     *
     * @return el nombre de la plantilla de inicio
     */
    @GetMapping("/")
    public String home() {
        return "inicio"; 
    }
}
