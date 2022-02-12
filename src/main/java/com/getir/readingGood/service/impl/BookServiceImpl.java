package com.getir.readingGood.service.impl;

import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.repositories.BookRepository;
import com.getir.readingGood.service.BookService;
import com.getir.readingGood.util.ReadingGoodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ozgurokka on 2/12/22 7:19 PM
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public BookEntity persistBook(BookEntity bookEntity) {
        bookEntity.setId(ReadingGoodUtils.getUUID());
        return bookRepository.save(bookEntity);
    }
}
