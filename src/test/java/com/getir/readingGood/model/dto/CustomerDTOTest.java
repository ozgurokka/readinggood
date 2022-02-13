package com.getir.readingGood.model.dto;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ozgurokka on 2/13/22 2:07 PM
 */
public class CustomerDTOTest {
    @Test
    public void testEquals() {
        CustomerDTO dto1 = CustomerDTO.builder()
                .email("email")
                .name("name")
                .surname("surname")
                .build();
        CustomerDTO dto2 = CustomerDTO.builder()
                .email("email")
                .name("name")
                .surname("surname")
                .build();

        assertTrue(dto1.equals(dto1));
        assertFalse(dto1.equals(null));

        dto1.setEmail("xx");
        assertFalse(dto1.equals(dto2));

        dto1.setEmail("email");
        assertTrue(dto1.equals(dto2));


        dto1.setName("xx");
        assertFalse(dto1.equals(dto2));

        dto1.setName("name");
        assertTrue(dto1.equals(dto2));

        dto1.setSurname("xx");
        assertFalse(dto1.equals(dto2));

        dto1.setSurname("surname");
        assertTrue(dto1.equals(dto2));
    }
}
