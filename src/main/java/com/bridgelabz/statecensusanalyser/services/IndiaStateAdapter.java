package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.CensusDAO;
import com.bridgelabz.statecensusanalyser.models.StateCSV;

import java.util.List;

public class IndiaStateAdapter extends CensusAdapter {
    @Override
    public List<CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        List<CensusDAO> indiaStateList = super.loadCensusData(StateCSV.class, csvFilePath[0]);
        return indiaStateList;
    }
}
