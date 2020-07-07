package com.bridgelabz.statecensusanalyser.models;

import com.opencsv.bean.CsvBindByName;
public class IndiaCensusCSV {
    public IndiaCensusCSV() {
    }

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int densityPerSqKm;

    public IndiaCensusCSV(String state, int areaInSqKm, int densityPerSqKm, int population) {
        this.state = state;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
        this.population = population;
    }
}

