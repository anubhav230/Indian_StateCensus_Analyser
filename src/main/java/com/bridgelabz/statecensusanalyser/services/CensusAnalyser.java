package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.CensusDAO;
import com.bridgelabz.statecensusanalyser.utility.JsonWrite;
import com.google.gson.Gson;
import java.util.Comparator;
import java.util.List;
import static com.bridgelabz.statecensusanalyser.services.CensusAdapter.censusDaoList;
import static com.bridgelabz.statecensusanalyser.utility.CheckNull.checkNull;

public class CensusAnalyser {

    public enum Country {
        INDIA_CENSUS, STATE_CODE, US
    }

    /**
     *
     * @param country
     *
     * @return
     * @throws CensusAnalyserException
     */
    public List<CensusDAO> loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        return new CensusAdapterFactory().getCensusData(country, csvFilePath);
    }

    /**
     *
     * @param type
     * @return
     * @throws CensusAnalyserException
     */
    public String sortCensusData(String type) throws CensusAnalyserException {
        String sortedStateCensusJson;
        Comparator<CensusDAO> censusComparator;
        checkNull();
        switch (type) {
            case "usPopulation":
                censusComparator = Comparator.comparing(census -> census.usPopulation);
                censusDaoList.sort(censusComparator.reversed());
                sortedStateCensusJson = new Gson().toJson(censusDaoList);
                return sortedStateCensusJson;
            case "state":
                 new IndiaCensusAdapter().censusDaoList.sort(((Comparator<CensusDAO>)
                        (census1, census2) -> census2.state.compareTo(census1.state)).reversed());
                sortedStateCensusJson = new Gson().toJson(censusDaoList);
                return sortedStateCensusJson;
            case "StateCode":
                censusDaoList.sort(((Comparator<CensusDAO>)
                        (census1, census2) -> census2.StateCode.compareTo(census1.StateCode)).reversed());
                JsonWrite.writeJson("./src/test/resources/IndiaStateCodeJson.json", censusDaoList);
                sortedStateCensusJson = new Gson().toJson(censusDaoList);
                return sortedStateCensusJson;
            case "population":
                censusComparator = Comparator.comparing(census -> census.population);
                censusDaoList.sort(censusComparator);
                sortedStateCensusJson = new Gson().toJson(censusDaoList);
                JsonWrite.writeJson("./src/test/resources/IndiaMostPopulatedStateJson.json", censusDaoList);
                return sortedStateCensusJson;
            case "densityPerSqKm":
                censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
                censusDaoList.sort(censusComparator.reversed());
                sortedStateCensusJson = new Gson().toJson(censusDaoList);
                JsonWrite.writeJson("./src/test/resources/IndiaStateCensusDataJson.json", censusDaoList);
                return sortedStateCensusJson;
            case "areaInSqKm":
                censusComparator = Comparator.comparing(census -> census.areaInSqKm);
                censusDaoList.sort(censusComparator);
                sortedStateCensusJson = new Gson().toJson(censusDaoList);
                JsonWrite.writeJson("./src/test/resources/IndiaLargestStateByArea.json", censusDaoList);
                return sortedStateCensusJson;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}