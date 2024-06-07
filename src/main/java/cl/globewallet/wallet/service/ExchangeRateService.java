package cl.globewallet.wallet.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * Servicio para obtener tasas de cambio de divisas.
 */
@Service
public class ExchangeRateService {

    private static final String API_URL = "https://mindicador.cl/api";

    /**
     * Obtiene la tasa de cambio actual del dólar estadounidense (USD).
     *
     * @return la tasa de cambio del dólar
     * @throws IllegalStateException si no se puede obtener el valor del dólar desde la API de Mindicador.cl
     */
    public double getDollarExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/dolar";
        IndicatorResponse response = restTemplate.getForObject(url, IndicatorResponse.class);

        if (response != null && response.getSerie() != null && !response.getSerie().isEmpty()) {
            return response.getSerie().get(0).getValor();
        } else {
            throw new IllegalStateException("No se pudo obtener el valor del dólar desde la API de Mindicador.cl");
        }
    }

    /**
     * Clase interna para representar la respuesta de la API del indicador.
     */
    private static class IndicatorResponse {
        private List<Serie> serie;

        /**
         * Obtiene la lista de series de valores.
         *
         * @return la lista de series
         */
        public List<Serie> getSerie() {
            return serie;
        }
    }

    /**
     * Clase interna para representar una serie de valores.
     */
    private static class Serie {
        private double valor;

        /**
         * Obtiene el valor de la serie.
         *
         * @return el valor
         */
        public double getValor() {
            return valor;
        }
    }
}
