package com.getir.readingGood.exception;

/**
 * Created by ozgurokka on 2/12/22 7:59 PM
 */
public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String id){
            super(" Customer Id: '" + id + "' not found !");
        }
}
