package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.services.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import javax.imageio.IIOException;

public class CensusAnalyserTest {

    StateCensusAnalyser stateCensusAnalyser;
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            stateCensusAnalyser = new StateCensusAnalyser();
            int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.txt";
        stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.getFileExtension(WRONG_CSV_FILE_PATH);
        }catch (CensusAnalyserException e){
            System.out.println(e.getMessage());
            //Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }
}
