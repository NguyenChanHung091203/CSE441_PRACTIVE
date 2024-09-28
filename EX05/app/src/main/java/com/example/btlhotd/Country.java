package com.example.btlhotd;

public class Country {
    public int flag;
    private String countryName;
    private String countryCapital;

    public Country(int flag, String countryName, String countryCapital) {
        this.flag = flag;
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    public int getFlag() {
        return flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

}
