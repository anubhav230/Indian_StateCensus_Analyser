package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.models.IndiaCensusCSV;
import com.bridgelabz.statecensusanalyser.models.StateCSV;
import com.bridgelabz.statecensusanalyser.models.USCensusData;
import com.bridgelabz.statecensusanalyser.services.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.bridgelabz.statecensusanalyser.services.CensusAnalyser.Country.*;

public class CensusAnalyserTest {
    CensusAnalyser censusAnalyser;

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            List numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            censusAnalyser = new CensusAnalyser(STATE_CODE);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
        }
    }

    //UC2
    @Test
    public void givenIndianStateCodeCSVFile_ReturnsCorrectRecords() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
        try {
            censusAnalyser = new CensusAnalyser(STATE_CODE);
            List numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongFile_ShouldThrowException() {
        String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(STATE_CODE);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongType_ShouldThrowException() {
        String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
        try {
            censusAnalyser = new CensusAnalyser(STATE_CODE);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongDelimiter_ShouldThrowException() {
        String WRONG_CSV_FILE_WRONG_DELIMITER = "./src/test/resources/IndianStateCensusData2.csv";
        try {
            censusAnalyser = new CensusAnalyser(STATE_CODE);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCodeCSVFile_WithWrongHeader_ShouldThrowException() {
        String WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/IndianStateCensusData3.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(WRONG_HEADER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
        }
    }

    //us
    @Test
    public void givenUsCensusCSVFile_ReturnsCorrectRecords() throws CensusAnalyserException {
        String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
        try {
            censusAnalyser = new CensusAnalyser(US);
            List numOfRecords = censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(45, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //uc3
    @Test
    public void giveIndianCensusData_WhenSortOnState_ShouldReturnSortedResult() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.sortCensusData("state");
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", indiaCensusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveStateCode_WhenSortStateCode_ShouldReturnSortedResult() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
        try {
            censusAnalyser = new CensusAnalyser(STATE_CODE);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortStateData = censusAnalyser.sortCensusData("stateCode");
            StateCSV[] stateCSV = new Gson().fromJson(sortStateData, StateCSV[].class);
            Assert.assertEquals("AD", stateCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //5
    @Test
    public void giveIndianCensusData_WhenSortOnPopulation_ShouldReturnSortedResult() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.sortCensusData("population");
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(607688, indiaCensusCSV[0].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortOnReversePopulation_ShouldReturnSortedResult() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.sortCensusData("population");
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, indiaCensusCSV[indiaCensusCSV.length - 1].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //6
    @Test
    public void giveIndianCensusData_WhenSortOnPopulatedDensity_ShouldReturnSortedResult() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.sortCensusData("densityPerSqKm");
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, indiaCensusCSV[indiaCensusCSV.length - 1].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //7
    @Test
    public void giveIndianCensusData_WhenAreaInSqkb_ShouldReturnSortedResult() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.sortCensusData("areaInSqKm");
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, indiaCensusCSV[indiaCensusCSV.length - 1].areaInSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveUsCensusData_WhenSortDescending_ShouldReturnSortedResult() {
        String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(US);
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.sortCensusData("usPopulation");
            USCensusData[] usCensusData = new Gson().fromJson(sortCensusData, USCensusData[].class);
            Assert.assertEquals(37253956, usCensusData[usCensusData.length - 1].usPopulation);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void giveIndiaCensus_WhenSortMostPopulation_ShouldReturnSortedResultState() {
        String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData2 = censusAnalyser.sortCensusData("population");
            IndiaCensusCSV[] indiaCensusData2 = new Gson().fromJson(sortCensusData2, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", indiaCensusData2[indiaCensusData2.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveUsCensusData_WhenSortMostPopulationState_ShouldReturnSortedResultState() {
        String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(US);
            censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH);

            String sortCensusData = censusAnalyser.sortCensusData("usPopulation");
            USCensusData[] usCensusData = new Gson().fromJson(sortCensusData, USCensusData[].class);
            Assert.assertEquals("California", usCensusData[usCensusData.length - 1].usState);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
