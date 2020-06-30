package com.bridgelabz.statecensusanalyser.services;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCsvBuilder<>();
    }
}
