package cl.globewallet.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.service.UserService;

/**
 * BaseController es una clase abstracta que proporciona funcionalidad común
 * para todos los controladores en la aplicación.
 * Incluye métodos para obtener el usuario actualmente autenticado.
 */
public abstract class BaseController {

    @Autowired
    protected UserService userService;

    /**
     * Obtiene el usuario actualmente autenticado.
     * 
     * @return el usuario actualmente autenticado, o {@code null} si no hay
     *         un usuario autenticado.
     */
    protected User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userService.findByUserRUT(username).orElse(null);
        }
        return null;
    }
}
