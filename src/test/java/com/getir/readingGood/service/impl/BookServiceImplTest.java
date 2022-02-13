package com.getir.readingGood.service.impl;

import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.repositories.BookRepository;
import com.getir.readingGood.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by ozgurokka on 2/13/22 2:13 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplTest {
    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Test
    public void persistBook() {
        BookEntity bookEntity = BookEntity.builder()
                .stock(20)
                .name("ozgur")
                .id("1")
                .build();

        BookEntity bookEntityReturn = BookEntity.builder()
                .stock(20)
                .name("ozgur")
                .id("1")
                .build();

        Mockito.when(bookRepository.save(bookEntity)).thenReturn(bookEntityReturn);

        BookEntity c = bookService.persistBook(bookEntity);

        assertEquals(c, bookEntityReturn);
    }
}
