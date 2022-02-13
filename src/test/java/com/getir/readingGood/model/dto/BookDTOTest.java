package com.getir.readingGood.model.dto;

import com.getir.readingGood.model.entity.BookEntity;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ozgurokka on 2/13/22 1:20 PM
 */
public class BookDTOTest {


    @Test
    public void testEquals() {
        BookDTO book1 = BookDTO.builder().name("name").stock(10).build();
        BookDTO book2= BookDTO.builder().name("name").stock(10).build();

        assertTrue(book1.equals(book2));
        assertFalse(book1.equals(null));

        book1.setName("falseName");
        assertFalse(book1.equals(book2));

        book1.setName("name");
        assertTrue(book1.equals(book2));

        book1.setStock(9);
        assertFalse(book1.equals(book2));

        book1.setStock(10);
        assertTrue(book1.equals(book2));





    }
}
