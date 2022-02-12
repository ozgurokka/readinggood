package com.getir.readingGood.service;

import com.getir.readingGood.model.entity.OrderEntity;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by ozgurokka on 2/9/22 8:53 PM
 */
public interface OrderService {
    public OrderEntity saveOrder(OrderEntity orderEntity);
    public OrderEntity getOrder(String id);
    public List<OrderEntity> getCustomerOrders(String id);
    public List<OrderEntity> getOrdersFromTo(Date from, Date to);
}
