package com.getir.readingGood.repositories;

import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * Created by ozgurokka on 2/11/22 7:18 PM
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<BookEntity,String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from BookEntity a where a.id = :id")
    Optional<BookEntity> findBookWithPessimisticLock(String id);
}
