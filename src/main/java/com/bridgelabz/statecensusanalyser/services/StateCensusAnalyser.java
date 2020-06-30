package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    /**
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndiaCensusCSV> censusCSVIterator = this.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }



    public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<StateCSV> stateCSVIterator = this.getCSVFileIterator(reader, StateCSV.class);
            Iterable<StateCSV> csvIterable = () -> stateCSVIterator;
            int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }





    private <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
        try{
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        }
        catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException("there is improper header",
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }


    /**
     *
     * @param filePath
     * @throws CensusAnalyserException
     */
    public void getFileExtension(String filePath) throws CensusAnalyserException {
        boolean result = filePath.endsWith(".csv");
        if (!result) {
            throw new CensusAnalyserException("wrong file Type",
                    CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
    }
}
