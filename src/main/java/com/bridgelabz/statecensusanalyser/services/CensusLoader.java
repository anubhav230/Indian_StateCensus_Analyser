package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.CensusDAO;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.bridgelabz.statecensusanalyser.models.UsCensusData;
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

public class CensusLoader {

    public <E>List loadCensusData(CensusAnalyser.Country country, String...csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA_CENSUS))
            return this.loadCensusData(IndiaCensusCSV.class, csvFilePath);
        else if (country.equals(CensusAnalyser.Country.STATE_CODE))
            return this.loadCensusData(StateCSV.class, csvFilePath);
        else if(country.equals(CensusAnalyser.Country.US))
             return this.loadCensusData(UsCensusData.class, csvFilePath);
        else throw new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
    }

    public static List<CensusDAO> censusDaoList = new ArrayList<>();
    public  <E> List loadCensusData(Class<E> classType, String... csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, classType);
            switch (classType.getSimpleName()) {
                case "IndiaCensusCSV":
                    censusDaoList.clear();
                    while (csvFileIterator.hasNext())
                        this.censusDaoList.add(new CensusDAO((IndiaCensusCSV) csvFileIterator.next()));
                    break;
                case "UsCensusData":
                    censusDaoList.clear();
                    while (csvFileIterator.hasNext())
                        this.censusDaoList.add(new CensusDAO((UsCensusData) csvFileIterator.next()));
                    break;
                case "StateCSV":
                    censusDaoList.clear();
                    while (csvFileIterator.hasNext())
                        this.censusDaoList.add(new CensusDAO((StateCSV) csvFileIterator.next()));
                    break;
            }
            return censusDaoList;
        } catch (IOException | RuntimeException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }


}
