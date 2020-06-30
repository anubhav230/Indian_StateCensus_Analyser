package com.bridgelabz.statecensusanalyser.services;

public class CSVBuilderException extends Exception {

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_FILE_TYPE, INCORRECT_DELIMITER, UNABLE_TO_PARSE
    }

    ExceptionType type;
    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}

