package com.getir.readingGood.service;

import com.getir.readingGood.model.entity.BookEntity;

/**
 * Created by ozgurokka on 2/12/22 7:18 PM
 */
public interface BookService {
    public BookEntity persistBook(BookEntity bookEntity);
    public BookEntity updateBookStock(String id,int stock);
}
