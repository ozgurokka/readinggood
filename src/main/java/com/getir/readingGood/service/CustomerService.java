package com.getir.readingGood.service;

import com.getir.readingGood.model.entity.CustomerEntity;

/**
 * Created by ozgurokka on 2/12/22 1:54 PM
 */
public interface CustomerService {
    public CustomerEntity registerNewCustomer(CustomerEntity ce);

    public CustomerEntity findByEmail(String mail);
}
