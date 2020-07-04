package com.bridgelabz.statecensusanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class UsCensusData {

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

    @Override
    public String toString() {
        return "UsCensusData{" +
                "stateId='" + stateId + '\'' +
                ", state='" + usState + '\'' +
                ", population=" + usPopulation +
                ", housingUnits='" + housingUnits + '\'' +
                ", TotalArea='" + totalArea + '\'' +
                ", waterArea='" + waterArea + '\'' +
                '}';
    }
}
