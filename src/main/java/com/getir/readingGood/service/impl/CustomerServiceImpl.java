package com.getir.readingGood.service.impl;

//import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.repositories.CustomerRepository;
import com.getir.readingGood.service.CustomerService;
import com.getir.readingGood.util.ReadingGoodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by ozgurokka on 2/12/22 1:57 PM
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerEntity registerNewCustomer(CustomerEntity ce) {
        if(null != findByEmail(ce.getEmail())){
            throw new CustomerAlreadyExistException(ce.getEmail());
        }
        ce.setId(ReadingGoodUtils.getUUID());
        ce.setRegisterTime(Timestamp.from(Instant.now()));
        return customerRepository.save(ce);
    }

    @Override
    public CustomerEntity findByEmail(String mail) {
        return customerRepository.findByEmail(mail);
    }
}
