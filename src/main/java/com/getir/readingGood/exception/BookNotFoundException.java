package com.getir.readingGood.exception;

/**
 * Created by ozgurokka on 2/12/22 9:20 PM
 */
public class BookNotFoundException  extends RuntimeException{
    public BookNotFoundException(String id){
        super(" Book is '" +id+ "' not found !");
    }
}