package com.getir.readingGood.service;

import com.getir.readingGood.model.entity.OrderEntity;

/**
 * Created by ozgurokka on 2/9/22 8:53 PM
 */
public interface OrderService {
    public OrderEntity saveOrder(OrderEntity orderEntity);
    public OrderEntity getOrder(String id);
}
