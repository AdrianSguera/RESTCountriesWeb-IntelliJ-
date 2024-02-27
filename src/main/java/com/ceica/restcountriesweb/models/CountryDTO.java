package com.ceica.restcountriesweb.models;

public class CountryDTO {
    private String name, region, flag, coin, cca3;
    private String[] capital;
    private int population;

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCca3() {
        return cca3;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public static CountryDTO fromCountryDAO(CountryDAO countryDAO){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(countryDAO.name.common);
        countryDTO.setFlag(countryDAO.flags.png);
        countryDTO.setPopulation(countryDAO.population);
        countryDTO.setCapital(countryDAO.capital);
        countryDTO.setCca3(countryDAO.cca3);
        if (countryDAO.currencies!=null) {
            String keySet = (String) countryDAO.currencies.keySet().toArray()[0];
            countryDTO.setCoin(countryDAO.currencies.get(keySet).name);
        } else
            countryDTO.setCoin("none");
        return countryDTO;
    }
}
