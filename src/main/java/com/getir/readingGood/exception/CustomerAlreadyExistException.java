package com.getir.readingGood.exception;

/**
 * Created by ozgurokka on 2/12/22 9:31 PM
 */
public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException(String email){
        super(" Email adress '" +email+ "' has already registered !");
    }
}
