package cl.globewallet.wallet.service;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Servicio para formatear balances en diferentes monedas.
 */
@Service
public class BalanceFormatterService {

    private static final DecimalFormatSymbols clpSymbols;
    private static final DecimalFormatSymbols usdSymbols;

    static {
        clpSymbols = new DecimalFormatSymbols(Locale.forLanguageTag("es-CL"));
        clpSymbols.setGroupingSeparator('.');
        clpSymbols.setDecimalSeparator(',');

        usdSymbols = new DecimalFormatSymbols(Locale.US);
        usdSymbols.setGroupingSeparator(',');
        usdSymbols.setDecimalSeparator('.');

        CLP_FORMAT = new DecimalFormat("#,##0", clpSymbols);   
        USD_FORMAT = new DecimalFormat("#,##0.00", usdSymbols); 
    }

    private static final DecimalFormat CLP_FORMAT;
    private static final DecimalFormat USD_FORMAT;

    private static final Map<String, DecimalFormat> formatMap = new HashMap<>();

    static {
        formatMap.put("CLP", CLP_FORMAT);
        formatMap.put("USD", USD_FORMAT);
    }

    /**
     * Formatea un balance según la moneda especificada.
     *
     * @param balance  el balance a formatear
     * @param currency la moneda del balance
     * @return el balance formateado
     */
    public String format(double balance, String currency) {
        DecimalFormat formatter = formatMap.getOrDefault(currency.toUpperCase(), USD_FORMAT);
        return formatter.format(balance);
    }

    /**
     * Formatea un balance con el símbolo de la moneda especificada.
     *
     * @param balance  el balance a formatear
     * @param currency la moneda del balance
     * @return el balance formateado con el símbolo de la moneda
     */
    public String formatWithCurrency(double balance, String currency) {
        DecimalFormat formatter = formatMap.getOrDefault(currency.toUpperCase(), USD_FORMAT);
        return currency + " " + formatter.format(balance);
    }
}
