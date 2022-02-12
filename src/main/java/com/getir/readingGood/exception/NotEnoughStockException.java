package com.getir.readingGood.exception;

/**
 * Created by ozgurokka on 2/12/22 9:30 PM
 */
public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(String id, int requested, int stock){
        super("Not Enough Stock for book id: " + id + " Stock: " + stock +" Requested: "+ requested);
    }
}
