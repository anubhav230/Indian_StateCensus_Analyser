package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.services.StateCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {

    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        try {
            stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        try {
            stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }


    //UC2
    @Test
    public void givenIndianCodeCSVFile_ReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
        try {
            int numOfRecords = stateCensusAnalyser.loadStateCode(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        try {
            stateCensusAnalyser.loadStateCode(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }


    @Test
    public void givenIndianCodeCSVFile_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        try {
            stateCensusAnalyser.loadStateCode(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void givenIndianCodeCSVFile_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            stateCensusAnalyser.loadStateCode(WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void givenIndianCodeCSVFile_WithWrongHeader_ShouldThrowException() {
        String WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            stateCensusAnalyser.loadStateCode(WRONG_HEADER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    //uc3
    @Test
    public void giveIndianCensusData_WhenSortOnState_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
            stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", indiaCensusCSV[0].state);
        } catch (CensusAnalyserException e) {

        }
    }
}
