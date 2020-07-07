package com.bridgelabz.statecensusanalyser.adapter;

import com.bridgelabz.statecensusanalyser.dao.CensusDAO;
import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;

import java.util.List;

public class IndiaCensusAdapter extends CensusAdapter {

    @Override
    public List<CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        List<CensusDAO> indiaCensusList = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        return indiaCensusList;
    }
}
