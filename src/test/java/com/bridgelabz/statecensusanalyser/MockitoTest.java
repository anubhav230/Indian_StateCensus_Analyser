package com.bridgelabz.statecensusanalyser;

import com.bridgelabz.statecensusanalyser.dao.CensusDAO;
import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.statecensusanalyser.services.CensusAnalyser;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;

import static com.bridgelabz.statecensusanalyser.services.CensusAnalyser.Country.US;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {
    List<CensusDAO> censusDaoList = new ArrayList<>();

    @Mock
    CensusAnalyser censusAnalyser = mock(CensusAnalyser.class);

    @Test
    public void givenIndiaCensus() {
        String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";
        try {
            censusAnalyser = new CensusAnalyser(US);
            when(censusAnalyser.loadCensusData(US_CENSUS_CSV_FILE_PATH)).thenReturn(censusDaoList);
            OngoingStubbing<List<CensusDAO>> numOfRecords = when(censusAnalyser
                                            .loadCensusData(US_CENSUS_CSV_FILE_PATH));
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
