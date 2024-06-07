package cl.globewallet.wallet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Representa una cuenta bancaria en el sistema.
 */
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    @JsonBackReference
    private User user;

    @Column(nullable = false, length = 45)
    private String accountType;

    @Column(nullable = false)
    private double accountBalance;

    @Column(nullable = false, length = 45)
    private String bankName;

    @Column(nullable = false, length = 45)
    private String accountNumber;

    @Column(nullable = false, length = 5) // Formato MM/YY
    private String expiryDate;

    @Column(nullable = false, length = 45)
    private String accountHolderName;

    @ManyToOne
    @JoinColumn(name = "currencyID", nullable = false)
    private Currency currency;

    @Transient
    private String formattedBalance;

    // Getters y Setters

    /**
     * Obtiene el ID de la cuenta.
     * 
     * @return el ID de la cuenta
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * Establece el ID de la cuenta.
     * 
     * @param accountID el ID de la cuenta
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    /**
     * Obtiene el usuario propietario de la cuenta.
     * 
     * @return el usuario propietario de la cuenta
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario propietario de la cuenta.
     * 
     * @param user el usuario propietario de la cuenta
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene el tipo de cuenta.
     * 
     * @return el tipo de cuenta
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Establece el tipo de cuenta.
     * 
     * @param accountType el tipo de cuenta
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Obtiene el saldo de la cuenta.
     * 
     * @return el saldo de la cuenta
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Establece el saldo de la cuenta.
     * 
     * @param accountBalance el saldo de la cuenta
     */
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Obtiene el nombre del banco.
     * 
     * @return el nombre del banco
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Establece el nombre del banco.
     * 
     * @param bankName el nombre del banco
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Obtiene el número de la cuenta.
     * 
     * @return el número de la cuenta
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Establece el número de la cuenta.
     * 
     * @param accountNumber el número de la cuenta
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Obtiene la fecha de expiración de la cuenta.
     * 
     * @return la fecha de expiración de la cuenta
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Establece la fecha de expiración de la cuenta.
     * 
     * @param expiryDate la fecha de expiración de la cuenta
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Obtiene el nombre del titular de la cuenta.
     * 
     * @return el nombre del titular de la cuenta
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Establece el nombre del titular de la cuenta.
     * 
     * @param accountHolderName el nombre del titular de la cuenta
     */
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    /**
     * Obtiene la moneda de la cuenta.
     * 
     * @return la moneda de la cuenta
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Establece la moneda de la cuenta.
     * 
     * @param currency la moneda de la cuenta
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Obtiene el saldo formateado de la cuenta.
     * 
     * @return el saldo formateado de la cuenta
     */
    public String getFormattedBalance() {
        return formattedBalance;
    }

    /**
     * Establece el saldo formateado de la cuenta.
     * 
     * @param formattedBalance el saldo formateado de la cuenta
     */
    public void setFormattedBalance(String formattedBalance) {
        this.formattedBalance = formattedBalance;
    }
}
