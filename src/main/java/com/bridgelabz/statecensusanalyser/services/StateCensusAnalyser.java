package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
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
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            Iterator<IndiaCensusCSV> censusCSVIterator = new OpenCsvBuilder().getCSVFileIterator(reader, IndiaCensusCSV.class);
            return this.getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }


    public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){

            Iterator<StateCSV> stateCSVIterator = new OpenCsvBuilder().getCSVFileIterator(reader, StateCSV.class);
            return this.getCount(stateCSVIterator);
        } catch (IOException e) {
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



}
