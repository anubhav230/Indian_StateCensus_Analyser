package com.bridgelabz.statecensusanalyser.adapter;

import com.bridgelabz.statecensusanalyser.dao.CensusDAO;
import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.services.CensusAnalyser;

import java.util.List;

public class CensusAdapterFactory {
    public List<CensusDAO> getCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA_CENSUS))
            return new IndiaCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyser.Country.STATE_CODE))
            return new IndiaStateAdapter().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
    }
}
