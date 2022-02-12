package com.getir.readingGood.exception;

/**
 * Created by ozgurokka on 2/12/22 9:56 PM
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}