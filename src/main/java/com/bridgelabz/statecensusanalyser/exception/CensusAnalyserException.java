package com.bridgelabz.statecensusanalyser.exception;

public class CensusAnalyserException extends Exception {

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_FILE_TYPE, INCORRECT_DELIMITER
    }

    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

//    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
//        super(message, cause);
//        this.type = type;
//    }
}
