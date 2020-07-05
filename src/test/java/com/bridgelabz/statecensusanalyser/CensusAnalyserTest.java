package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.CensusDAO;
import com.bridgelabz.statecensusanalyser.services.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.bridgelabz.statecensusanalyser.services.CensusAnalyser.Country.*;

public class CensusAnalyserTest {

    CensusAnalyser stateCensusAnalyser = new CensusAnalyser();

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            List numOfRecords = stateCensusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        try {
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        try {
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    //UC2
    @Test
    public void givenIndianStateCodeCSVFile_ReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
        try {
            List numOfRecords = stateCensusAnalyser.loadCensusData(STATE_CODE, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        try {
            stateCensusAnalyser.loadCensusData(STATE_CODE, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        try {
            stateCensusAnalyser.loadCensusData(STATE_CODE, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            stateCensusAnalyser.loadCensusData(STATE_CODE, WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongHeader_ShouldThrowException() {
        String WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            stateCensusAnalyser.loadCensusData(STATE_CODE, WRONG_HEADER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    //us
    @Test
    public void givenUsCensusCSVFile_ReturnsCorrectRecords() {
        String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
        try {
            List numOfRecords = stateCensusAnalyser.loadCensusData(US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(45, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //uc3
    @Test
    public void giveIndianCensusData_WhenSortOnState_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            CensusDAO[] indiaCensusCSV = new Gson().fromJson(sortCensusData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", indiaCensusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveStateCode_WhenSortStateCode_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
            stateCensusAnalyser.loadCensusData(STATE_CODE, INDIA_CENSUS_CSV_FILE_PATH);
            String sortStateData = stateCensusAnalyser.getStateCodeWiseSortedStateCode();
            CensusDAO[] StateCSV = new Gson().fromJson(sortStateData, CensusDAO[].class);
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
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData();
            CensusDAO[] indiaCensusCSV = new Gson().fromJson(sortCensusData, CensusDAO[].class);
            Assert.assertEquals(199812341, indiaCensusCSV[indiaCensusCSV.length - 1].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //6
    @Test
    public void giveIndianCensusData_WhenSortOnPopulatedDensity_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulatedDensityWiseSortedCensusData();
            CensusDAO[] indiaCensusCSV = new Gson().fromJson(sortCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, indiaCensusCSV[indiaCensusCSV.length - 1].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //7
    @Test
    public void giveIndianCensusData_WhenAreaInSqkb_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getLargestStateByArea();
            CensusDAO[] indiaCensusCSV = new Gson().fromJson(sortCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, indiaCensusCSV[indiaCensusCSV.length - 1].areaInSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveUsCensusData_WhenSortDescending_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
            stateCensusAnalyser.loadCensusData(US, INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulatedDensityWiseSortedUsCensusData();
            CensusDAO[] usCensusData = new Gson().fromJson(sortCensusData, CensusDAO[].class);
            Assert.assertEquals(37253956, usCensusData[usCensusData.length - 1].usPopulation);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveUsCensusData_WhenSortLeastPopulated_ShouldReturnSortedResult() {
        try {
            String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
            stateCensusAnalyser.loadCensusData(US, INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulatedDensityWiseSortedUsCensusData();
            CensusDAO[] usCensusData = new Gson().fromJson(sortCensusData, CensusDAO[].class);
            Assert.assertEquals(601723, usCensusData[0].usPopulation);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveIndiaCensus_WhenSortMostPopulation_ShouldReturnSortedResultState() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            stateCensusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData2 = stateCensusAnalyser.getPopulationWiseSortedCensusData();
            CensusDAO[] indiaCensusData2 = new Gson().fromJson(sortCensusData2, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", indiaCensusData2[indiaCensusData2.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveUsCensusData_WhenSortMostPopulation_ShouldReturnSortedResultState() {
        String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
        try {
            stateCensusAnalyser.loadCensusData(US, US_CENSUS_CSV_FILE_PATH);
            String sortCensusData = stateCensusAnalyser.getPopulatedStateSortedUsCensusAndIndiaCensusData();
            CensusDAO[] usCensusData = new Gson().fromJson(sortCensusData, CensusDAO[].class);
            Assert.assertEquals("California", usCensusData[usCensusData.length - 1].usState);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }

}
