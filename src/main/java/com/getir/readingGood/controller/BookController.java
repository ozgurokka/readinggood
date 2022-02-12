package com.getir.readingGood.controller;

import com.getir.readingGood.exception.CustomerAlreadyExistException;
import com.getir.readingGood.model.dto.BookDTO;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.service.BookService;
import com.getir.readingGood.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by ozgurokka on 2/11/22 7:21 PM
 */
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(path = "/api/book")
    public ResponseEntity<BookEntity> persistBook(@Valid @RequestBody BookDTO bookDTO) {
        // convert entity
        BookEntity bookEntityRequest = ObjectMapperUtils.map(bookDTO,BookEntity.class);

        //save
        BookEntity bookEntity = bookService.persistBook(bookEntityRequest);

        //return result
        return ResponseEntity.ok(bookEntity);

    }

    @PostMapping(path = "/api/book/{book-id}/stock/{stock}")
    public ResponseEntity<BookEntity> updateBookStock(@PathVariable(name = "book-id", required = true) String bookId,@PathVariable(name = "stock", required = true) @Min(0) Integer stock) {

        //save
        BookEntity bookEntity = bookService.updateBookStock(bookId,stock);

        //return result
        return ResponseEntity.ok(bookEntity);

    }
}
