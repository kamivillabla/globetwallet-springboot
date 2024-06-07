package cl.globewallet.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.globewallet.wallet.model.Currency;

import java.util.Optional;

/**
 * Repositorio para la entidad Currency.
 * Proporciona métodos para realizar operaciones CRUD en la tabla Currencies.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    
    /**
     * Encuentra una moneda por su nombre.
     *
     * @param currencyName el nombre de la moneda que se desea encontrar
     * @return una moneda opcional si se encuentra una moneda con el nombre proporcionado, o una moneda opcional vacía si no se encuentra ninguna moneda
     */
    Optional<Currency> findByCurrencyName(String currencyName);
}
