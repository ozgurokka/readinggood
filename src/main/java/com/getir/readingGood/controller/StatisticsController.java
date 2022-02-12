package com.getir.readingGood.controller;

import com.getir.readingGood.exception.NotFoundException;
import com.getir.readingGood.model.dto.OrderResponseDTO;
import com.getir.readingGood.model.dto.StatisticDTO;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.service.OrderService;
import com.getir.readingGood.service.StatisticsService;
import com.getir.readingGood.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozgurokka on 2/12/22 6:29 PM
 */
@RestController
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;
    // find customer orders
    @GetMapping(path = "/api/statistics/customer/{customer-id}")
    public ResponseEntity<List<StatisticDTO>> getCustomersOrders(@PathVariable(name = "customer-id", required = true) String customerId) throws NotFoundException {
        List<StatisticDTO> customerMonthlyStatistics = statisticsService.getCustomerMonthlyStatistics(customerId);

//        List<OrderResponseDTO> responseDTOS = new ArrayList<>();
//        orderEntityList.forEach(orderEntity -> {
//            OrderResponseDTO responseDTO = ObjectMapperUtils.map(orderEntity, OrderResponseDTO.class);
//            responseDTOS.add(responseDTO);
//        });ß

        return ResponseEntity.ok(customerMonthlyStatistics);
    }
}
