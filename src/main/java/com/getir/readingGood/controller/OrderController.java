package com.getir.readingGood.controller;

import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.exception.CustomerNotFoundException;
import com.getir.readingGood.exception.NotFoundException;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.dto.OrderDTO;
import com.getir.readingGood.model.dto.OrderResponseDTO;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.service.OrderService;
import com.getir.readingGood.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable(name = "order-id", required = true) String orderId) throws NotFoundException {
        OrderResponseDTO responseDTO = ObjectMapperUtils.map(orderService.getOrder(orderId), OrderResponseDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "/api/order/from/{start-date}/to/{end-date}")
    public ResponseEntity<List<OrderResponseDTO>>  getOrdersFromTo (@PathVariable(name="start-date", required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @PathVariable(name="end-date", required=true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        List<OrderEntity> orderEntityList = orderService.getOrdersFromTo(from,to);
        List<OrderResponseDTO> responseDTOS = new ArrayList<>();
        orderEntityList.forEach(orderEntity -> {
            OrderResponseDTO responseDTO = ObjectMapperUtils.map(orderEntity, OrderResponseDTO.class);
            responseDTOS.add(responseDTO);
        });

        return ResponseEntity.ok(responseDTOS);
    }
}
