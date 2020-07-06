package com.bridgelabz.statecensusanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class StateCSV {
    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;
}
