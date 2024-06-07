package cl.globewallet.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.globewallet.wallet.model.User;
import cl.globewallet.wallet.service.UserService;
import cl.globewallet.wallet.util.PasswordValidator;

/**
 * Controlador para manejar las acciones relacionadas con los usuarios, 
 * como el registro y el inicio de sesión.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Muestra el formulario de registro.
     *
     * @param model el modelo para la vista
     * @return el nombre de la plantilla de registro
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Registra una nueva cuenta de usuario.
     *
     * @param user el objeto User con los datos del formulario
     * @param model el modelo para la vista
     * @return la redirección a la página de inicio de sesión si el registro es exitoso, 
     *         o la página de registro si hay un error
     */
    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") User user, Model model) {
        if (!PasswordValidator.isValid(user.getUserPassword())) {
            model.addAttribute("errorMessage", "La contraseña debe contener al menos 8 caracteres, incluyendo mayúsculas, minúsculas, números y símbolos (@#$%^&+=).");
            return "register";
        }

        String encodedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodedPassword);
        userService.save(user);
        return "redirect:/user/login"; 
    }

    /**
     * Muestra el formulario de inicio de sesión.
     *
     * @param error el mensaje de error opcional que indica si hubo un problema con el inicio de sesión
     * @param model el modelo para la vista
     * @return el nombre de la plantilla de inicio de sesión
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "RUT o contraseña incorrectos.");
        }
        return "login";
    }
}
