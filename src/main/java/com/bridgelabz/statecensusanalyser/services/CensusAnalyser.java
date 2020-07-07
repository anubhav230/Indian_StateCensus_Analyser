package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.adapter.CensusAdapterFactory;
import com.bridgelabz.statecensusanalyser.dao.CensusDAO;
import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.utility.JsonWrite;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.bridgelabz.statecensusanalyser.adapter.CensusAdapter.censusDaoList;
import static com.bridgelabz.statecensusanalyser.utility.CheckNull.checkNull;

public class CensusAnalyser {

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
        return new CensusAdapterFactory().getCensusData(country, csvFilePath);
    }

    Comparator<CensusDAO> censusComparator;
    ArrayList censusDTO;

    /**
     * method for checking type and sorting
     * @param type
     * @return
     * @throws CensusAnalyserException
     */
    public String sortCensusData(String type) throws CensusAnalyserException {
        checkNull();
        String sortedStateCensusJson;
        checkNull();
        switch (type) {
            case "usPopulation":
                censusComparator = Comparator.comparing(census -> census.usPopulation);
                sort();
                sortedStateCensusJson = new Gson().toJson(censusDTO);
                JsonWrite.writeJson("./src/test/resources/usPopulationJson.json", censusDTO);
                return sortedStateCensusJson;
            case "state":
                censusComparator = Comparator.comparing(census -> census.state);
                sort();
                sortedStateCensusJson = new Gson().toJson(censusDTO);
                return sortedStateCensusJson;
            case "stateCode":
                censusComparator = Comparator.comparing(census -> census.stateCode);
                sort();
                sortedStateCensusJson = new Gson().toJson(censusDTO);
                JsonWrite.writeJson("./src/test/resources/IndiaStateCodeJson.json", censusDTO);
                return sortedStateCensusJson;
            case "population":
                censusComparator = Comparator.comparing(census -> census.population);
                sort();
                sortedStateCensusJson = new Gson().toJson(censusDTO);
                return sortedStateCensusJson;
            case "densityPerSqKm":
                censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
                sort();
                sortedStateCensusJson = new Gson().toJson(censusDTO);
                JsonWrite.writeJson("./src/test/resources/densityPerSqKmJson.json", censusDTO);
                return sortedStateCensusJson;
            case "areaInSqKm":
                censusComparator = Comparator.comparing(census -> census.areaInSqKm);
                sort();
                sortedStateCensusJson = new Gson().toJson(censusDTO);
                JsonWrite.writeJson("./src/test/resources/areaInSqKmJson.json", censusDTO);
                return sortedStateCensusJson;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    /**
     * sort method for sorting list
     * @return
     */
    public List sort() {
        censusDTO = censusDaoList.stream()
                .sorted(censusComparator)
                .map(censusDAO -> censusDAO.getCensusDTOS(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return censusDTO;
    }
}

