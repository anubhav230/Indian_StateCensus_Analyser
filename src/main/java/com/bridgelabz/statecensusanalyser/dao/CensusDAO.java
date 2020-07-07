package com.bridgelabz.statecensusanalyser.dao;

import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.bridgelabz.statecensusanalyser.models.USCensusData;
import com.bridgelabz.statecensusanalyser.services.CensusAnalyser;

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
    public String stateCode;
    public String state;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(StateCSV stateCSV) {
        stateCode = stateCSV.stateCode;
    }

    public <E> CensusDAO(USCensusData usCensusData) {
        housingUnits = usCensusData.housingUnits;
        usPopulation = usCensusData.usPopulation;
        stateId = usCensusData.stateId;
        totalArea = usCensusData.totalArea;
        waterArea = usCensusData.waterArea;
        usState = usCensusData.usState;
    }
    public Object getCensusDTOS(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusData(housingUnits, usPopulation, stateId, totalArea, waterArea, usState);
        else if (country.equals(CensusAnalyser.Country.INDIA_CENSUS))
            return new IndiaCensusCSV(state, areaInSqKm, (int) densityPerSqKm,(int) population);
        return new StateCSV(stateCode);
    }
}
