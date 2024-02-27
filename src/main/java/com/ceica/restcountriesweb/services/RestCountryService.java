package com.ceica.restcountriesweb.services;


import com.ceica.restcountriesweb.models.CountryDAO;
import com.ceica.restcountriesweb.models.CountryDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestCountryService implements IRestCountry {
    @Override
    public String[] getRegions() {
        String data = getFromApi("https://restcountries.com/v3.1/all");
        Gson gson = new Gson();
        CountryDAO[] countries = gson.fromJson(data, CountryDAO[].class);
        List<String> regions = new ArrayList<>();
        for (CountryDAO country : countries)
            if (!regions.contains(country.region))
                regions.add(country.region);
        String[] regionsArray = new String[regions.size()];
        for (int i = 0; i < regions.size(); i++) {
            regionsArray[i] = regions.get(i);
        }
        return regionsArray;
    }

    @Override
    public List<CountryDTO> getCountriesByRegion(String region) {
        String data = getFromApi("https://restcountries.com/v3.1/region/" + region);
        return getCountryDTOS(data);
    }

    private List<CountryDTO> getCountryDTOS(String data) {
        Gson gson = new Gson();
        List<CountryDAO> countriesDAO = gson.fromJson(data, new TypeToken<List<CountryDAO>>(){}.getType());
        if (countriesDAO != null) {
            return countriesDAO.stream()
                    .map(CountryDTO::fromCountryDAO)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public CountryDTO getCountryByCca3(String cca3) {
        String data = getFromApi("https://restcountries.com/v3.1/alpha/" + cca3);
        Gson gson = new Gson();
        CountryDAO[] countries = gson.fromJson(data, CountryDAO[].class);
        return CountryDTO.fromCountryDAO(countries[0]);
    }

    private static String getFromApi(String petition) {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(petition))
                .build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CountryDTO> getCountryByName(String name) {
        String data = getFromApi("https://restcountries.com/v3.1/name/" + name);
        return getCountryDTOS(data);
    }
}
