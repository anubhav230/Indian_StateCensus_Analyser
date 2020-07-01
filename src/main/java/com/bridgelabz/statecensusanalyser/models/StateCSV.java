package com.bridgelabz.statecensusanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class StateCSV {

    @CsvBindByName(column = "State Name", required = true)
    public String StateName;


    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                ", Population='" + StateName + '\'' +
                ", DensityPerSqKm='" + StateCode + '\'' +
                '}';
    }
}
