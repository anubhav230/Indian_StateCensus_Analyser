package com.bridgelabz.statecensusanalyser.utility;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;

import static com.bridgelabz.statecensusanalyser.services.CensusLoader.censusDaoList;

public class CheckNull {
    public static void checkNull() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0)
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
    }
}
