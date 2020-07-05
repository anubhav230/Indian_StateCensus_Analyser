package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.CensusDAO;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.bridgelabz.statecensusanalyser.models.UsCensusData;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;

import static com.bridgelabz.statecensusanalyser.services.CensusLoader.censusDaoList;

public class StateCensusAnalyser {

    /**
     * method for loading india census data
     *
     * @param csvFilePath
     * @return
     * @throws
     */
    public List loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        return new CensusLoader().loadCensusData(csvFilePath, IndiaCensusCSV.class);
    }


    /**
     * method for loading india state data
     *
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public List loadStateCode(String csvFilePath) throws CensusAnalyserException {
        return new CensusLoader().loadCensusData(csvFilePath, StateCSV.class);
    }

    /**
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public List loadUsData(String csvFilePath) throws CensusAnalyserException {
        return new CensusLoader().loadCensusData(csvFilePath, UsCensusData.class);
    }

    /**
     * method for shorting india census data
     *
     * @return
     * @throws CensusAnalyserException
     */
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        System.out.println(censusDaoList);
        if (censusDaoList == null || censusDaoList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        new CensusLoader().censusDaoList.sort(((Comparator<CensusDAO>)
                (census1, census2) -> census2.state.compareTo(census1.state)).reversed());
        String sortedStateCensusJson = new Gson().toJson(censusDaoList);
        return sortedStateCensusJson;
    }

    /**
     * method for sorting state code wise
     *
     * @return
     * @throws CensusAnalyserException
     */
    public String getStateCodeWiseSortedStateCode() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        censusDaoList.sort(((Comparator<CensusDAO>)
                (census1, census2) -> census2.StateCode.compareTo(census1.StateCode)).reversed());
        String sortedStateCensusJson = new Gson().toJson(censusDaoList);
        return sortedStateCensusJson;
    }

    /**
     * checking null and shorting population
     *
     * @return
     * @throws CensusAnalyserException
     */
    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        Sort.sort(censusComparator, censusDaoList);
        String sortedStateCensusJson = new Gson().toJson(censusDaoList);
        return sortedStateCensusJson;
    }

    /**
     * method for shorting population aria wise
     *
     * @return
     * @throws CensusAnalyserException
     */
    public String getPopulatedDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        Sort.sort(censusComparator, censusDaoList);
        String sortedStateCensusJson = new Gson().toJson(censusDaoList);
        JsonWrite.writeJson("./src/test/resources/IndiaStateCensusDataJson.json", censusDaoList);
        return sortedStateCensusJson;
    }

    /**
     * d=shorting largest state
     *
     * @return
     * @throws CensusAnalyserException
     */
    public String getLargestStateByArea() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        Sort.sort(censusComparator, censusDaoList);
        String sortedStateCensusJson = new Gson().toJson(censusDaoList);
        JsonWrite.writeJson("./src/test/resources/IndiaLargestStateByArea.json", censusDaoList);
        return sortedStateCensusJson;
    }

    /**
     * method for get population density area
     *
     * @return
     * @throws CensusAnalyserException
     */
    public String getPopulatedDensityWiseSortedUsCensusData() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.usPopulation);
        Sort.sort(censusComparator, censusDaoList);
        String sortedStateCensusJson = new Gson().toJson(censusDaoList);
        JsonWrite.writeJson("./src/test/resources/UsStateCensusDataDescending.json", censusDaoList);
        return sortedStateCensusJson;
    }

    /**
     * @return
     * @throws CensusAnalyserException
     */
    public String getPopulatedStateSortedUsCensusAndIndiaCensusData() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.usPopulation);
        Sort.sort(censusComparator, censusDaoList);
        String sortedStateCensusJson = new Gson().toJson(censusDaoList);
        return sortedStateCensusJson;
    }
}