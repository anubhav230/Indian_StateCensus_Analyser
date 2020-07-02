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

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    //List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaCensusDAO> censusList = null;
    List<StateCSV> stateCSVList = null;

    public StateCensusAnalyser() {
        this.censusList = new ArrayList<IndiaCensusDAO>();
    }

    /**
     * method for loading india census data
     *
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            while (csvFileIterator.hasNext()){
                this.censusList.add(new IndiaCensusDAO(csvFileIterator.next()));
            }
            return this.censusList.size();
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

    /**
     * loading state dode data
     *
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCSVList = csvBuilder.getCSVFileList(reader, StateCSV.class);
            return stateCSVList.size();
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

    /**
     * @param integer
     * @param <E>
     * @return
     */
    private <E> int getCount(Iterator<E> integer) {
        Iterable<E> csvIterable = () -> integer;
        int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEntries;
    }

    /**
     * @return
     * @throws CensusAnalyserException
     */
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    /**
     * @return
     * @throws CensusAnalyserException
     */
    public String getStateCodeWiseSortedStateCode() throws CensusAnalyserException {
        if (stateCSVList == null || stateCSVList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<StateCSV> stateCode = Comparator.comparing(census -> census.StateCode);
        this.sortStateCode(stateCode);
        String sortedStateCodeJson = new Gson().toJson(stateCSVList);
        return sortedStateCodeJson;
    }

    /**
     * @return
     * @throws CensusAnalyserException
     */
    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    /**
     * @return
     * @throws CensusAnalyserException
     */
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

    /**
     * @return
     * @throws CensusAnalyserException
     */
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

    /**
     * @param censusComparator
     */
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

    /**
     * method for sorting state code
     *
     * @param stateCode
     */
    private void sortStateCode(Comparator<StateCSV> stateCode) {
        for (int i = 0; i < stateCSVList.size() - 1; i++) {
            for (int j = 0; j < stateCSVList.size() - i - 1; j++) {
                StateCSV census1 = stateCSVList.get(j);
                StateCSV census2 = stateCSVList.get(j + 1);
                if (stateCode.compare(census1, census2) > 0) {
                    stateCSVList.set(j, census2);
                    stateCSVList.set(j + 1, census1);
                }
            }
        }
    }

    /**
     * sorting in descending order
     *
     * @param censusComparator
     */
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