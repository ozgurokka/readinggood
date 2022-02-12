package com.getir.readingGood.util;

/**
 * Created by ozgurokka on 2/11/22 7:03 PM
 */
public enum OrderState {

    CANCELED(0),
    PLACED(1),
    IN_PROGRESS(2),
    COMPLETED(3);

    public int status;

    OrderState(int status) {
        this.status = status;
    }
}
