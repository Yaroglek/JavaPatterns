package org.yaroglek.patterns.extern.countrycode;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class RestCountriesApiImpl implements RestCountriesApi {
    private final RestTemplate restTemplate;

    @Override
    public List<Map<String, Object>> getCountryData(String countryName) {
        String url = "https://restcountries.com/v3.1/name/" + countryName;

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}
