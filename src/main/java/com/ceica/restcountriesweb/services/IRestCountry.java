package com.ceica.restcountriesweb.services;



import com.ceica.restcountriesweb.models.CountryDTO;

import java.util.List;

public interface IRestCountry {
    public String[] getRegions();
    public List<CountryDTO> getCountriesByRegion(String region);
    public CountryDTO getCountryByCca3(String cca3);
}
