package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.bridgelabz.statecensusanalyser.models.UsCensusData;
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
            e.printStackTrace();
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
            String sortCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", indiaCensusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //4
    @Test
    public void giveIndianStateData_WhenSortStateCode_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
            stateCensusAnalyser.loadStateCode(INDIA_CENSUS_CSV_FILE_PATH);
            String sortStateData = stateCensusAnalyser.getStateCodeWiseSortedStateCode();
            StateCSV[] StateCSV = new Gson().fromJson(sortStateData, StateCSV[].class);
            Assert.assertEquals("AD", StateCSV[0].StateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //5
    @Test
    public void giveIndianCensusData_WhenSortOnPopulation_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
            stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, indiaCensusCSV[indiaCensusCSV.length -1].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //6
    @Test
    public void giveIndianCensusData_WhenSortOnPopulatedDensity_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
            stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulatedDensityWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, indiaCensusCSV[0].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //7
    @Test
    public void giveIndianCensusData_WhenAreaInSqkb_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
            stateCensusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getLargestStateByArea();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, indiaCensusCSV[0].areaInSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //us
    @Test
    public void givenUsCensusCSVFile_ReturnsCorrectRecords() {
        String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
        try {
            int numOfRecords = stateCensusAnalyser.loadUsData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(45, numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void giveUsCensusData_WhenSortDescending_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
            stateCensusAnalyser.loadUsData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulatedDensityWiseSortedUsCensusData();
            UsCensusData[] usCensusData = new Gson().fromJson(sortCensusData, UsCensusData[].class);
            Assert.assertEquals(37253956, usCensusData[0].usPopulation);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveUsCensusData_WhenSortLeastPopulated_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
            stateCensusAnalyser.loadUsData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulatedDensityWiseSortedUsCensusData();
            UsCensusData[] usCensusData = new Gson().fromJson(sortCensusData, UsCensusData[].class);
            Assert.assertEquals(601723, usCensusData[usCensusData.length - 1].usPopulation);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
