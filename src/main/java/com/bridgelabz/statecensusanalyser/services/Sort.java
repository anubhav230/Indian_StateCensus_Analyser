package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.models.CensusDAO;

import java.util.Comparator;
import java.util.List;

public class Sort {
    static void sort(Comparator<CensusDAO> censusComparator, List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                CensusDAO census1 = (CensusDAO) list.get(j);
                CensusDAO census2 = (CensusDAO) list.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
    }

}

