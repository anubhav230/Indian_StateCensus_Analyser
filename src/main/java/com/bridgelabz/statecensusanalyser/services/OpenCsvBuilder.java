package com.bridgelabz.statecensusanalyser.services;

import com.bridgelabz.statecensusanalyser.exception.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCsvBuilder<E> implements ICSVBuilder {
    /**
     * for handling Csv file
     * @param reader
     * @param csvClass
     * @param <E>
     * @return
     * @throws CensusAnalyserException
     */
    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("there is improper header",
                    CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }
}
