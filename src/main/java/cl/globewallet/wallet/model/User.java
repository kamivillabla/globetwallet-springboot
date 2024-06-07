package cl.globewallet.wallet.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * Representa un usuario en el sistema.
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(nullable = false, length = 45)
    private String userName;

    @Column(nullable = false, length = 45)
    private String userLastName;

    @Column(nullable = false, unique = true, length = 45)
    private String userEmail;

    @Column(nullable = false, length = 255)
    private String userPassword;

    @Column(nullable = false, unique = true, length = 10)
    private String userRUT;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Account> accounts;

    // Getters y Setters

    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param userID el ID del usuario
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param userName el nombre del usuario
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return el apellido del usuario
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param userLastName el apellido del usuario
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return el correo electrónico del usuario
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param userEmail el correo electrónico del usuario
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param userPassword la contraseña del usuario
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Obtiene el RUT del usuario.
     *
     * @return el RUT del usuario
     */
    public String getUserRUT() {
        return userRUT;
    }

    /**
     * Establece el RUT del usuario.
     *
     * @param userRUT el RUT del usuario
     */
    public void setUserRUT(String userRUT) {
        this.userRUT = userRUT;
    }

    /**
     * Obtiene las cuentas asociadas al usuario.
     *
     * @return una lista de cuentas asociadas al usuario
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Establece las cuentas asociadas al usuario.
     *
     * @param accounts una lista de cuentas asociadas al usuario
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
