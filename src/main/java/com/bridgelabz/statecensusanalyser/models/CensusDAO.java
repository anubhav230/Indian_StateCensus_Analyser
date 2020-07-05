package com.bridgelabz.statecensusanalyser.models;

public class CensusDAO {

    public String usState;
    public double waterArea;
    public double totalArea;
    public String stateId;
    public int usPopulation;
    public int housingUnits;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String srNo;
    public String tin;
    public String stateName;
    public String StateCode;
    public String state;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(StateCSV stateCSV) {
        StateCode = stateCSV.StateCode;
        stateName = stateCSV.stateName;
        tin = stateCSV.tin;
        srNo = stateCSV.srNo;
    }

    public <E> CensusDAO(UsCensusData usCensusData) {
        housingUnits = usCensusData.housingUnits;
        usPopulation = usCensusData.usPopulation;
        stateId = usCensusData.stateId;
        totalArea = usCensusData.totalArea;
        waterArea = usCensusData.waterArea;
        usState = usCensusData.usState;
    }



}
