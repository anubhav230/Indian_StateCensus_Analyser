package com.bridgelabz.statecensusanalyser.models;

public class IndiaCensusDAO {

    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String srNo;
    public String tin;
    public String StateName;
    public String StateCode;
    public String state;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public IndiaCensusDAO(StateCSV stateCSV) {
        StateCode = stateCSV.StateCode;
        StateName = stateCSV.StateName;
        tin = stateCSV.tin;
        srNo = stateCSV.srNo;
    }
}
