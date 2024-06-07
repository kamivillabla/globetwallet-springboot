package cl.globewallet.wallet.model;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Representa una transacción en el sistema.
 */
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    @ManyToOne
    @JoinColumn(name = "senderAccountID", nullable = true)
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiverAccountID", nullable = true)
    private Account receiverAccount;

    @Column(nullable = false)
    private double transactionAmount;

    @Column(nullable = false)
    private Date transactionDate;

    @Column(nullable = false, length = 45)
    private String transactionType;

    @Column(nullable = false, length = 10)
    private String transactionCurrency;

    @Transient
    private String formattedAmount;

    // Getters y Setters

    /**
     * Obtiene el ID de la transacción.
     *
     * @return el ID de la transacción
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * Establece el ID de la transacción.
     *
     * @param transactionID el ID de la transacción
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Obtiene la cuenta del remitente.
     *
     * @return la cuenta del remitente
     */
    public Account getSenderAccount() {
        return senderAccount;
    }

    /**
     * Establece la cuenta del remitente.
     *
     * @param senderAccount la cuenta del remitente
     */
    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    /**
     * Obtiene la cuenta del receptor.
     *
     * @return la cuenta del receptor
     */
    public Account getReceiverAccount() {
        return receiverAccount;
    }

    /**
     * Establece la cuenta del receptor.
     *
     * @param receiverAccount la cuenta del receptor
     */
    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    /**
     * Obtiene la cantidad de la transacción.
     *
     * @return la cantidad de la transacción
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Establece la cantidad de la transacción.
     *
     * @param transactionAmount la cantidad de la transacción
     */
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Obtiene la fecha de la transacción.
     *
     * @return la fecha de la transacción
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Establece la fecha de la transacción.
     *
     * @param transactionDate la fecha de la transacción
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Obtiene el tipo de la transacción.
     *
     * @return el tipo de la transacción
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Establece el tipo de la transacción.
     *
     * @param transactionType el tipo de la transacción
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Obtiene la moneda de la transacción.
     *
     * @return la moneda de la transacción
     */
    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    /**
     * Establece la moneda de la transacción.
     *
     * @param transactionCurrency la moneda de la transacción
     */
    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    /**
     * Obtiene el monto formateado de la transacción.
     *
     * @return el monto formateado de la transacción
     */
    public String getFormattedAmount() {
        return formattedAmount;
    }

    /**
     * Establece el monto formateado de la transacción.
     *
     * @param formattedAmount el monto formateado de la transacción
     */
    public void setFormattedAmount(String formattedAmount) {
        this.formattedAmount = formattedAmount;
    }
}
