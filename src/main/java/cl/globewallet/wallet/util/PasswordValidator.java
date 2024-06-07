package cl.globewallet.wallet.util;

import java.util.regex.Pattern;

/**
 * Utilidad para validar contraseñas.
 */
public class PasswordValidator {

    /**
     * Patrón de validación de contraseñas.
     * Debe contener al menos un dígito, una letra minúscula, una letra mayúscula,
     * un carácter especial (@#$%^&+=) y tener una longitud mínima de 8 caracteres.
     */
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Valida una contraseña contra el patrón definido.
     *
     * @param password la contraseña a validar
     * @return true si la contraseña es válida, false en caso contrario
     */
    public static boolean isValid(final String password) {
        return pattern.matcher(password).matches();
    }
}
