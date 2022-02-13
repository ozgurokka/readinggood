package com.getir.readingGood.controller;

//import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.exception.NotFoundException;
import com.getir.readingGood.model.dto.OrderResponseDTO;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.service.CustomerService;
import com.getir.readingGood.service.OrderService;
import com.getir.readingGood.util.ObjectMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozgurokka on 2/12/22 2:00 PM
 */
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/api/person")
    public ResponseEntity<CustomerEntity> register(@Valid  @RequestBody CustomerDTO cdto) throws CustomerAlreadyExistException {
        // convert entity
        CustomerEntity customerEntityRequest = ObjectMapperUtils.map(cdto,CustomerEntity.class);

        //save
        CustomerEntity customerEntity = customerService.registerNewCustomer(customerEntityRequest);

        //return result
        return ResponseEntity.ok(customerEntity);

    }

    // find customer orders
    @GetMapping(path = "/api/person/{customer-id}")
    public ResponseEntity<List<OrderResponseDTO>> getCustomersOrders(@PathVariable(name = "customer-id", required = true) String customerId) throws NotFoundException {
        List<OrderEntity> orderEntityList = orderService.getCustomerOrders(customerId);
        List<OrderResponseDTO> responseDTOS = new ArrayList<>();
        orderEntityList.forEach(orderEntity -> {
            OrderResponseDTO responseDTO = ObjectMapperUtils.map(orderEntity, OrderResponseDTO.class);
            responseDTOS.add(responseDTO);
        });

        return ResponseEntity.ok(responseDTOS);
    }

}
