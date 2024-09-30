package com.example.tema04ejercicio01.modelos;

import java.util.Objects;

public class Country {
    private String countryCode;
    private String countryName;
    private long population;
    private String capital;
    private String isoAlpha3;
    private int flagResource;

    public Country() {

    }

    public Country(String countryCode, String countryName, long population, String capital, String isoAlpha3, int flagResource) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.population = population;
        this.capital = capital;
        this.isoAlpha3 = isoAlpha3;
        this.flagResource = flagResource;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public int getFlagResource() {
        return flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return population == country.population && Objects.equals(countryCode, country.countryCode) && Objects.equals(countryName, country.countryName) && Objects.equals(capital, country.capital) && Objects.equals(isoAlpha3, country.isoAlpha3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, countryName, population, capital, isoAlpha3);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", population=" + population +
                ", capital='" + capital + '\'' +
                ", isoAlpha3='" + isoAlpha3 + '\'' +
                '}';
    }
}
