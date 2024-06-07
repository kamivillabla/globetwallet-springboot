package cl.globewallet.wallet.model;

import jakarta.persistence.*;

/**
 * Representa una moneda en el sistema.
 */
@Entity
@Table(name = "Currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int currencyID;

    @Column(nullable = false, length = 45)
    private String currencyName;

    @Column(nullable = false, length = 10)
    private String currencySymbol;

    @Column(nullable = false)
    private float currencyValue;

    // Getters y Setters

    /**
     * Obtiene el ID de la moneda.
     * 
     * @return el ID de la moneda
     */
    public int getCurrencyID() {
        return currencyID;
    }

    /**
     * Establece el ID de la moneda.
     * 
     * @param currencyID el ID de la moneda
     */
    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    /**
     * Obtiene el nombre de la moneda.
     * 
     * @return el nombre de la moneda
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Establece el nombre de la moneda.
     * 
     * @param currencyName el nombre de la moneda
     */
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * Obtiene el símbolo de la moneda.
     * 
     * @return el símbolo de la moneda
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * Establece el símbolo de la moneda.
     * 
     * @param currencySymbol el símbolo de la moneda
     */
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    /**
     * Obtiene el valor de la moneda.
     * 
     * @return el valor de la moneda
     */
    public float getCurrencyValue() {
        return currencyValue;
    }

    /**
     * Establece el valor de la moneda.
     * 
     * @param currencyValue el valor de la moneda
     */
    public void setCurrencyValue(float currencyValue) {
        this.currencyValue = currencyValue;
    }
}
