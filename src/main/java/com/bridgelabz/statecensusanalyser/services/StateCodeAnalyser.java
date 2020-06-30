package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;

import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
public class StateCodeAnalyser {

    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<StateCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(StateCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCSV> censusCSVIterator = csvToBean.iterator();
            ;
            int namOfEateries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEateries++;
                StateCSV censusData = censusCSVIterator.next();
            }
            return namOfEateries;
        }catch (RuntimeException e) {
            throw new CensusAnalyserException("wrong delimiter",
                    CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public void getFileExtension(String filePath) throws CensusAnalyserException {
        boolean result = filePath.endsWith(".csv");
        if (!result) {
            throw new CensusAnalyserException("wrong file path",
                    CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
    }

}
