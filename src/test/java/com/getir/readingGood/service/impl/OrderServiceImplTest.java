package com.getir.readingGood.service.impl;

import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderDetailEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.repositories.BookRepository;
import com.getir.readingGood.repositories.CustomerRepository;
import com.getir.readingGood.repositories.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by ozgurokka on 2/13/22 6:50 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    BookRepository bookRepository;

    @Test
    public void saveOrder() {
        List<OrderDetailEntity> orderDetailEntityLis = new ArrayList<>();
        orderDetailEntityLis.add(OrderDetailEntity.builder().id("1").bookEntity(BookEntity.builder().id("1").build()).build());


        OrderEntity orderEntity = OrderEntity.builder()
                .amount(new BigDecimal(10))
                .status(1)
                .id("1")
                .customerEntity(CustomerEntity.builder().id("1").name("ozgur").build())
                .orderDetailEntityList(orderDetailEntityLis)
                .build();

        OrderEntity orderEntityReturn = OrderEntity.builder()
                .amount(new BigDecimal(10))
                .status(1)
                .id("1")
                .orderDetailEntityList(orderDetailEntityLis)
                .customerEntity(CustomerEntity.builder().name("ozgur").id("1").build())
                .build();



        Mockito.when(orderRepository.save(orderEntity)).thenReturn(orderEntityReturn);
        Mockito.when(customerRepository.findById("1")).thenReturn(Optional.of(CustomerEntity.builder().id("1").name("ozgur").build()));
        Mockito.when(bookRepository.findBookWithPessimisticLock("1")).thenReturn(Optional.of(BookEntity.builder().id("1").build()));

        OrderEntity c = orderService.saveOrder(orderEntity);

        assertEquals(c, orderEntityReturn);
    }
}
