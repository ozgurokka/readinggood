package com.getir.readingGood.controller;

import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.exception.CustomerNotFoundException;
import com.getir.readingGood.exception.NotFoundException;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.dto.OrderDTO;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.service.OrderService;
import com.getir.readingGood.util.ObjectMapperUtils;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ozgurokka on 2/12/22 11:11 PM
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(path = "/api/order")
    public ResponseEntity<OrderEntity> placeOrder(@Valid @RequestBody OrderDTO orderDTO) throws CustomerNotFoundException {
        //convert entitiy
        OrderEntity order = ObjectMapperUtils.map(orderDTO,OrderEntity.class);

        //save
        order = orderService.saveOrder(order);

        return ResponseEntity.ok(order);
    }

    // find an order by id
    @GetMapping(path = "/api/order/{order-id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "order-id", required = true) String orderId) throws NotFoundException {
        OrderDTO orderDTO = ObjectMapperUtils.map(orderService.getOrder(orderId),OrderDTO.class);
        return ResponseEntity.ok(orderDTO);
    }
}
