package com.getir.readingGood.service.impl;

import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.repositories.CustomerRepository;
import com.getir.readingGood.repositories.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

/**
 * Created by ozgurokka on 2/13/22 1:28 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {
    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Test
    public void saveCustomer() {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .surname("okka")
                .name("ozgur")
                .id("1")
                .build();

        CustomerEntity customerEntityReturn = CustomerEntity.builder()
                .surname("okka")
                .name("ozgur")
                .id("1")
                .build();

        Mockito.when(customerRepository.save(customerEntity)).thenReturn(customerEntityReturn);

        CustomerEntity c = customerService.registerNewCustomer(customerEntity);

        assertEquals(c, customerEntityReturn);
    }

    @Test
    public void findByEmail() {
        Instant now = Instant.now();
        CustomerEntity customerEntity = CustomerEntity.builder()
                .surname("okka")
                .name("ozgur")
                .email("1@1.com")
                .id("1")
                .build();

        CustomerEntity customerEntityReturn = CustomerEntity.builder()
                .surname("okka")
                .name("ozgur")
                .id("1")
                .email("1@1.com")
                .build();

        Mockito.when(customerRepository.findByEmail("1@1.com")).thenReturn(customerEntityReturn);

        CustomerEntity c = customerService.findByEmail("1@1.com");

        assertEquals(c, customerEntityReturn);
    }
}
