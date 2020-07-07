package com.bridgelabz.statecensusanalyser.adapter;

import com.bridgelabz.statecensusanalyser.dao.CensusDAO;
import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.USCensusData;

import java.util.List;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public List<CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        List<CensusDAO> usCensusList = super.loadCensusData(USCensusData.class, csvFilePath[0]);
        return usCensusList;
    }
}
