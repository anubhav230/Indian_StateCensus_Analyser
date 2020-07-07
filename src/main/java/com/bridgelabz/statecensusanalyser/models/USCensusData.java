package com.bridgelabz.statecensusanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class USCensusData {

    public USCensusData() {
    }

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String usState;

    @CsvBindByName(column = "Population", required = true)
    public int usPopulation;

    @CsvBindByName(column = "Housing units", required = true)
    public int housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "Water area", required = true)
    public double waterArea;

    public USCensusData(int housingUnits,int usPopulation, String stateId, double totalArea, double waterArea, String usState) {
        this.housingUnits = housingUnits;
        this.stateId = stateId;
        this.totalArea = totalArea;
        this.waterArea = waterArea;
        this.usState = usState;
        this.usPopulation= usPopulation;
    }
}
