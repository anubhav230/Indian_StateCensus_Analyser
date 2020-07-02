package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusDAO;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.csvbuilder.CSVBuilderException;
import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    List<IndiaCensusDAO> censusList;
    Map<String, IndiaCensusDAO> censusDAOMap = new HashMap<>();

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            while (csvFileIterator.hasNext()) {
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(csvFileIterator.next());
                this.censusDAOMap.put(indiaCensusDAO.state, indiaCensusDAO);
            }
            return censusDAOMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<StateCSV> csvIterator = csvBuilder.getCSVFileIterator(reader, StateCSV.class);
            while (csvIterator.hasNext()) {
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(csvIterator.next());
                this.censusDAOMap.put(indiaCensusDAO.StateCode, indiaCensusDAO);
            }
            return censusDAOMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;
    }


    public String getStateCodeWiseSortedStateCode() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> stateCode = Comparator.comparing(census -> census.StateCode);
        this.descendingSort(stateCode);
        String sortedStateCodeJson = new Gson().toJson(censusList);
        return sortedStateCodeJson;
    }


    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }


    public String getPopulatedDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.descendingSort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        try (Writer writer = new FileWriter("./src/test/resources/IndiaStateCensusDataJson.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedStateCensusJson;
    }


    public String getLargestStateByArea() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.descendingSort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        try (Writer writer = new FileWriter("./src/test/resources/IndiaLargestStateByArea.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedStateCensusJson;
    }

    private void sort(Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                IndiaCensusDAO census1 = censusList.get(j);
                IndiaCensusDAO census2 = censusList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }
            }
        }
    }

    private void descendingSort(Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                IndiaCensusDAO census1 = censusList.get(j);
                IndiaCensusDAO census2 = censusList.get(j + 1);
                if (censusComparator.compare(census1, census2) < 0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }
            }
        }
    }
}