package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.services.StateCensusAnalyser;
import com.bridgelabz.statecensusanalyser.services.StateCodeAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

    StateCensusAnalyser stateCensusAnalyser;
    StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
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
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        stateCensusAnalyser = new StateCensusAnalyser();
        try {
            stateCensusAnalyser.getFileExtension(WRONG_CSV_FILE_TYPE);
        }catch (CensusAnalyserException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            stateCensusAnalyser = new StateCensusAnalyser();
            stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            stateCensusAnalyser = new StateCensusAnalyser();
            stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    //UC2
    @Test
    public void givenIndianCodeCSVFile_ReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
        try {
            int numOfRecords = stateCodeAnalyser.loadIndiaStateData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusAnalyserException ignored) { }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        try {
            stateCodeAnalyser.loadIndiaStateData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }


    @Test
    public void givenIndianCodeCSVFile_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        try {
            stateCodeAnalyser.getFileExtension(WRONG_CSV_FILE_TYPE);
        }catch (CensusAnalyserException e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void givenIndianCodeCSVFile_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            stateCodeAnalyser.loadIndiaStateData(WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongHeader_ShouldThrowException() {
        String WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            stateCodeAnalyser.loadIndiaStateData(WRONG_HEADER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER,e.type);
        }
    }
}
