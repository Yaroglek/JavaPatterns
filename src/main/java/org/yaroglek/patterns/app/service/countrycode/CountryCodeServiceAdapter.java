package org.yaroglek.patterns.app.service.countrycode;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaroglek.patterns.extern.countrycode.RestCountriesApiClient;

import java.util.List;
import java.util.Map;

/**
 * Реализация паттерна Adapter.
 */
@Service
@AllArgsConstructor
public class CountryCodeServiceAdapter implements CountryCodeService {

    private final RestCountriesApiClient restCountriesApiClient;

    @Override
    public String getCountryCode(String countryName) {
        List<Map<String, Object>> countryData = restCountriesApiClient.getCountryData(countryName);
        if (!countryData.isEmpty()) {
            return (String) countryData.getFirst().get("cca2");
        }
        return null;
    }
}
