package com.getir.readingGood.repositories;

import com.getir.readingGood.model.entity.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ozgurokka on 2/12/22 1:43 PM
 */
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity,String> {
    CustomerEntity findByEmail(String mail);
}
