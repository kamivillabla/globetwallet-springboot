package cl.globewallet.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.globewallet.wallet.model.Currency;
import cl.globewallet.wallet.repository.CurrencyRepository;

import java.util.Optional;

/**
 * Servicio para manejar las operaciones relacionadas con las monedas.
 */
@Service
@Transactional
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Guarda una moneda en el repositorio.
     *
     * @param currency la moneda a guardar
     */
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    /**
     * Busca una moneda por su nombre.
     *
     * @param currencyName el nombre de la moneda
     * @return un Optional que contiene la moneda si se encuentra
     */
    public Optional<Currency> findByCurrencyName(String currencyName) {
        return currencyRepository.findByCurrencyName(currencyName);
    }

    /**
     * Asegura que una moneda exista en el repositorio. Si no existe, crea una nueva.
     *
     * @param currencyName   el nombre de la moneda
     * @param currencySymbol el símbolo de la moneda
     * @param currencyValue  el valor de la moneda
     * @return la moneda existente o la nueva moneda creada
     */
    public Currency ensureCurrencyExists(String currencyName, String currencySymbol, float currencyValue) {
        Optional<Currency> currencyOptional = findByCurrencyName(currencyName);
        if (currencyOptional.isEmpty()) {
            Currency currency = new Currency();
            currency.setCurrencyName(currencyName);
            currency.setCurrencySymbol(currencySymbol);
            currency.setCurrencyValue(currencyValue);
            save(currency);
            return currency;
        } else {
            return currencyOptional.get();
        }
    }
}
