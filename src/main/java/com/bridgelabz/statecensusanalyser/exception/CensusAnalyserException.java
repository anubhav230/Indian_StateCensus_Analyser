package com.bridgelabz.statecensusanalyser.exception;

public class CensusAnalyserException extends Exception {

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, NO_DATA , INVALID_COUNTRY, INVALID_FILE_PATH
    }

    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
