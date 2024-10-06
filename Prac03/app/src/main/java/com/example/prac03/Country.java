package com.example.prac03;

public class Country {
    private String name;
    private long population;
    private String details;
    private int flagResource;
    // Constructor, getters, and setters
    public Country(String name, long population, String details, int flag_vietnam) {
        this.name = name;
        this.population = population;
        this.details = details;
        this.flagResource = flagResource;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public String getDetails() {
        return details;
    }

    public int getFlagResource() {
        return flagResource;
    }
}

