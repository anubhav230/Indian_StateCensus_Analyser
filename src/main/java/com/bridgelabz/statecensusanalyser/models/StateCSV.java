package com.bridgelabz.statecensusanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class StateCSV {

    public StateCSV() {
    }
    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    public StateCSV(String stateCode) {
        this.stateCode = stateCode;
    }
}
