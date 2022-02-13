package com.getir.readingGood.model.entity;

import com.getir.readingGood.model.dto.CustomerDTO;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ozgurokka on 2/13/22 6:46 PM
 */
public class CustomerEntitiyTest {
    @Test
    public void testEquals() {
        CustomerEntity c1 = CustomerEntity.builder()
                .email("email")
                .name("name")
                .surname("surname")
                .id("1")
                .build();

        CustomerEntity c2  = CustomerEntity.builder()
                .email("email")
                .name("name")
                .surname("surname")
                .id("1")
                .build();

        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(null));

        c1.setEmail("xx");
        assertFalse(c1.equals(c2));

        c1.setEmail("email");
        assertTrue(c1.equals(c2));

        c1.setName("xx");
        assertFalse(c1.equals(c2));

        c1.setName("name");
        assertTrue(c1.equals(c2));

        c1.setSurname("xx");
        assertFalse(c1.equals(c2));

        c1.setSurname("surname");
        assertTrue(c1.equals(c2));
    }
}
