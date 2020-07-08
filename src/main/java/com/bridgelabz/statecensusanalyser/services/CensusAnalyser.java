package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.adapter.CensusAdapterFactory;
import com.bridgelabz.statecensusanalyser.dao.CensusDAO;
import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.utility.JsonWrite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Comparator<CensusDAO> censusComparator;
    ArrayList censusDTO;
    List<CensusDAO> censusDaoList = null;

    public enum Country {
        INDIA_CENSUS, STATE_CODE, US
    }

    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    /**
     * @return
     * @throws CensusAnalyserException
     */
    public List<CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        this.censusDaoList = new CensusAdapterFactory().getCensusData(country, csvFilePath);
        return censusDaoList;
    }



    /**
     * method for checking type and sorting
     *
     * @param type
     * @return
     * @throws CensusAnalyserException
     */
    public String sortCensusData(String type) throws CensusAnalyserException {
        checkNull();
        String sortedStateCensusJson;
        switch (type) {
            case "usPopulation":
                censusComparator = Comparator.comparing(census -> census.usPopulation);
                sort();
                sortedStateCensusJson = JsonWrite.writeJson
                        ("./src/test/resources/usPopulationJson.json", censusDTO);
                return sortedStateCensusJson;
            case "state":
                censusComparator = Comparator.comparing(census -> census.state);
                sort();
                sortedStateCensusJson = JsonWrite.writeJson
                        ("./src/test/resources/usPopulationJson.json", censusDTO);
                return sortedStateCensusJson;
            case "stateCode":
                censusComparator = Comparator.comparing(census -> census.stateCode);
                sort();
                sortedStateCensusJson = JsonWrite.writeJson
                        ("./src/test/resources/IndiaStateCodeJson.json", censusDTO);
                return sortedStateCensusJson;
            case "population":
                censusComparator = Comparator.comparing(census -> census.population);
                sort();
                sortedStateCensusJson = JsonWrite.writeJson
                        ("./src/test/resources/IndiaStateCodeJson.json", censusDTO);
                return sortedStateCensusJson;
            case "densityPerSqKm":
                censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
                sort();
                sortedStateCensusJson = JsonWrite.writeJson
                        ("./src/test/resources/densityPerSqKmJson.json", censusDTO);
                return sortedStateCensusJson;
            case "areaInSqKm":
                censusComparator = Comparator.comparing(census -> census.areaInSqKm);
                sort();
                sortedStateCensusJson = JsonWrite.writeJson
                        ("./src/test/resources/areaInSqKmJson.json", censusDTO);
                return sortedStateCensusJson;
        }
        throw new IllegalStateException("Unexpected value: " + type);
    }

    /**
     * sort method for sorting list
     *
     * @return
     */
    public List sort() {
        censusDTO = censusDaoList.stream()
                .sorted(censusComparator)
                .map(censusDAO -> censusDAO.getCensusDTOS(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return censusDTO;
    }

    /**
     * method for checking null exception
     * @throws CensusAnalyserException
     */
    public void checkNull() throws CensusAnalyserException {
        if (censusDaoList == null || censusDaoList.size() == 0)
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
    }
}
