package com.getir.readingGood.repositories;

import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ozgurokka on 2/11/22 7:18 PM
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<BookEntity,String> {
}
