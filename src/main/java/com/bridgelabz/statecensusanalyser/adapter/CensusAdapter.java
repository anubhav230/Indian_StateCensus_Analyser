package com.bridgelabz.statecensusanalyser.adapter;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.dao.CensusDAO;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.bridgelabz.statecensusanalyser.models.USCensusData;
import com.csvbuilder.CSVBuilderException;
import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public  abstract class CensusAdapter {

    public abstract List<CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;

    public <E> List<CensusDAO> loadCensusData(Class<E> classType, String csvFilePath) throws CensusAnalyserException {
        List<CensusDAO> censusDaoList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, classType);
            switch (classType.getSimpleName()) {
                case "IndiaCensusCSV":
                    while (csvFileIterator.hasNext())
                        censusDaoList.add(new CensusDAO((IndiaCensusCSV) csvFileIterator.next()));
                    break;
                case "USCensusData":
                    while (csvFileIterator.hasNext())
                        censusDaoList.add(new CensusDAO((USCensusData) csvFileIterator.next()));
                    break;
                case "StateCSV":
                    while (csvFileIterator.hasNext())
                        censusDaoList.add(new CensusDAO((StateCSV) csvFileIterator.next()));
                    break;
            }
            return censusDaoList;
        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException("Wrong header or delimiter",
                    CensusAnalyserException.ExceptionType.INVALID_FILE_PATH);
        }
    }
}

