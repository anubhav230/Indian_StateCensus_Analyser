package com.bridgelabz.statecensusanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class StateCSV {
    @CsvBindByName(column = "SrNo", required = true)
    public int SrNo;

    @CsvBindByName(column = "StateName", required = true)
    public String StateName;

    @CsvBindByName(column = "TIN", required = true)
    public int TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State='" + SrNo + '\'' +
                ", Population='" + StateName + '\'' +
                ", AreaInSqKm='" + TIN + '\'' +
                ", DensityPerSqKm='" + StateCode + '\'' +
                '}';
    }
}
