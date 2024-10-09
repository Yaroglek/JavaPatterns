package org.yaroglek.patterns.extern.countrycode;

import java.util.List;
import java.util.Map;

public interface RestCountriesApi {
    List<Map<String, Object>> getCountryData(String countryName);
}
