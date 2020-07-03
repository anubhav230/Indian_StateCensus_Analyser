package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.CensusDAO;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.bridgelabz.statecensusanalyser.models.UsCensusData;
import com.csvbuilder.CSVBuilderException;
import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StateCensusAnalyser {
    List<CensusDAO> censusList = null;
    Collection<Object> records = null;
    Map<Object, Object> censusDAOMap = new HashMap<>();


    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusDAOMap = csvBuilder.getCSVFileHashMap(reader, IndiaCensusCSV.class);
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
            censusDAOMap = csvBuilder.getCSVFileHashMap(reader, StateCSV.class);
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

    public int loadUsData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusDAOMap = csvBuilder.getCSVFileHashMap(reader, UsCensusData.class);
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
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator, censusDAOMap);
        records = censusDAOMap.values();
        String sortedStateCensusJson = new Gson().toJson(records);
        return sortedStateCensusJson;
    }


    public String getStateCodeWiseSortedStateCode() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<StateCSV> censusComparator = Comparator.comparing(census -> census.StateCode);
        this.sort(censusComparator, censusDAOMap);
        records = censusDAOMap.values();
        String sortedStateCensusJson = new Gson().toJson(records);
        return sortedStateCensusJson;
    }


    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
        this.sort(censusComparator, censusDAOMap);
        records = censusDAOMap.values();
        String sortedStateCensusJson = new Gson().toJson(records);
        return sortedStateCensusJson;
    }


    public String getPopulatedDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.descendingSort(censusComparator, censusDAOMap);
        records = censusDAOMap.values();
        String sortedStateCensusJson = new Gson().toJson(records);
        try (Writer writer = new FileWriter("./src/test/resources/IndiaStateCensusDataJson.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusDAOMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedStateCensusJson;
    }


    public String getLargestStateByArea() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.descendingSort(censusComparator, censusDAOMap);
        records = censusDAOMap.values();
        String sortedStateCensusJson = new Gson().toJson(records);
        try (Writer writer = new FileWriter("./src/test/resources/IndiaLargestStateByArea.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusDAOMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedStateCensusJson;
    }

    public String getPopulatedDensityWiseSortedUsCensusData() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<UsCensusData> censusComparator = Comparator.comparing(census -> census.usPopulation);
        this.descendingSort(censusComparator, censusDAOMap);
        records = censusDAOMap.values();
        String sortedStateCensusJson = new Gson().toJson(records);
        try (Writer writer = new FileWriter("./src/test/resources/UsStateCensusDataDescending.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusDAOMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedStateCensusJson;
    }

    public String getPopulatedAreaWiseSortedUsCensusData() throws CensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<UsCensusData> censusComparator = Comparator.comparing(census -> census.totalArea);
        this.sort(censusComparator, censusDAOMap);
        records = censusDAOMap.values();
        String sortedStateCensusJson = new Gson().toJson(records);
        try (Writer writer = new FileWriter("./src/test/resources/UsStateCensusDataAreaWise.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusDAOMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedStateCensusJson;
    }

    private <E> void sort(Comparator censusComparator, Map<Object, Object> records) {
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = 0; j < records.size() - i - 1; j++) {
                E census1 = (E) records.get(j);
                E census2 = (E) records.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    records.put(j, census2);
                    records.put(j + 1, census1);
                }
            }
        }
    }

    private <E> void descendingSort(Comparator censusComparator, Map<Object, Object> records) {
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = 0; j < records.size() - i - 1; j++) {
                E census1 = (E) records.get(j);
                E census2 = (E) records.get(j + 1);
                if (censusComparator.compare(census1, census2) < 0) {
                    records.put(j, census2);
                    records.put(j + 1, census1);
                }
            }
        }
    }
}