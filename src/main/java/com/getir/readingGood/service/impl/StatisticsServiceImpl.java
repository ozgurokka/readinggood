package com.getir.readingGood.service.impl;

import com.getir.readingGood.model.dto.StatisticDTO;
import com.getir.readingGood.model.entity.OrderDetailEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.repositories.OrderRepository;
import com.getir.readingGood.service.StatisticsService;
import com.getir.readingGood.util.ReadingGoodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.Year;
import java.util.*;

/**
 * Created by ozgurokka on 2/12/22 6:36 PM
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<StatisticDTO> getCustomerMonthlyStatistics(String customerId) {

        // current year statistics
        int year = Calendar.getInstance().get(Calendar.YEAR);
        HashMap<String, StatisticDTO> hashMap = new HashMap<>();
        Instant start = Instant.parse(year+"-01-01T00:00:00.00Z");
        Instant end = Instant.parse(year+"-12-31T23:59:59.00Z");

        //get orders
        List<OrderEntity> orderEntityList = orderRepository.
                findAllByCustomerEntity_IdAndOrderTimeBetween(customerId, Timestamp.from(start),Timestamp.from(end));

        //clasify
        orderEntityList.forEach(orderEntity -> {

          String month = ReadingGoodUtils.getMonth(orderEntity.getOrderTime().toString().substring(5,7));

          if(!hashMap.containsKey(month)){
              hashMap.put(month, StatisticDTO.builder()
                      .bookCount(orderEntity.getOrderDetailEntityList().stream().mapToInt(OrderDetailEntity::getCount).sum())
                      .month(month)
                      .totalAmount(orderEntity.getAmount())
                      .orderCount(1)
                      .build());
          }else{
              StatisticDTO dto = hashMap.get(month);
              dto.setBookCount(dto.getBookCount() + orderEntity.getOrderDetailEntityList().stream().mapToInt(OrderDetailEntity::getCount).sum());
              dto.setOrderCount(dto.getOrderCount()+1);
              dto.setTotalAmount(dto.getTotalAmount().add(orderEntity.getAmount()));
              hashMap.put(month,dto);
          }

        });
        return new ArrayList<StatisticDTO>(hashMap.values());
    }
}
