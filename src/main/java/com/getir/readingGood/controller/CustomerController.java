package com.getir.readingGood.controller;

//import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.service.CustomerService;
import com.getir.readingGood.util.ObjectMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ozgurokka on 2/12/22 2:00 PM
 */
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping(path = "/api/person")
    public ResponseEntity<CustomerEntity> register(@Valid  @RequestBody CustomerDTO cdto) throws CustomerAlreadyExistException {
        // convert entity
        CustomerEntity customerEntityRequest = ObjectMapperUtils.map(cdto,CustomerEntity.class);

        //save
        CustomerEntity customerEntity = customerService.registerNewCustomer(customerEntityRequest);

        //return result
        return ResponseEntity.ok(customerEntity);

    }
}
