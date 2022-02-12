package com.getir.readingGood.repositories;

import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ozgurokka on 2/12/22 8:55 PM
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity,String> {
}
