package com.getir.readingGood.service.impl;

import com.getir.readingGood.exception.BookNotFoundException;
import com.getir.readingGood.exception.CustomerNotFoundException;
import com.getir.readingGood.exception.NotEnoughStockException;
import com.getir.readingGood.exception.NotFoundException;
import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.repositories.BookRepository;
import com.getir.readingGood.repositories.CustomerRepository;
import com.getir.readingGood.repositories.OrderRepository;
import com.getir.readingGood.service.OrderService;
import com.getir.readingGood.util.OrderState;
import com.getir.readingGood.util.ReadingGoodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by ozgurokka on 2/12/22 8:54 PM
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional
    public OrderEntity saveOrder(OrderEntity orderEntity) {

        Optional<CustomerEntity> customerEntity = customerRepository.findById(orderEntity.getCustomerEntity().getId());
        // customer if not found!
        if(!customerEntity.isPresent()){
            throw new CustomerNotFoundException(orderEntity.getCustomerEntity().getId()+"");
        }
        orderEntity.setId(ReadingGoodUtils.getUUID());
        orderEntity.setOrderTime(Timestamp.from(Instant.now()));
        orderEntity.setStatus(OrderState.PLACED.status);
        orderEntity.setCustomerEntity(customerEntity.get());

        orderEntity.getOrderDetailEntityList().forEach(orderDetailEntity-> orderDetailEntity.setOrderEntity(orderEntity));
        orderEntity.getOrderDetailEntityList().forEach(orderDetailEntity-> orderDetailEntity.setId(ReadingGoodUtils.getUUID()));

        orderEntity.getOrderDetailEntityList().forEach(orderDetailEntity -> {

            // find book with pesimistic lock.
            Optional<BookEntity> bookEntity = bookRepository.findBookWithPessimisticLock(orderDetailEntity.getBookEntity().getId());

            //controls
            if(!bookEntity.isPresent()){
                throw new BookNotFoundException(orderDetailEntity.getBookEntity().getId());
            }
            //Check enough stock for this book order;
            var remainStock = bookEntity.get().getStock() - orderDetailEntity.getCount();
            if(remainStock < 0){
                throw new NotEnoughStockException(orderDetailEntity.getBookEntity().getId(),orderDetailEntity.getCount(),bookEntity.get().getStock());
            }

            //update stock
            bookEntity.get().setStock(remainStock);

            orderDetailEntity.setBookEntity(bookEntity.get());
        });

        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity getOrder(String id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if(!orderEntity.isPresent()){
            throw  new NotFoundException("Order Not Found, id:" + id);
        }
        return orderEntity.get();
    }

    @Override
    public List<OrderEntity> getCustomerOrders(String id) {
        return orderRepository.findAllByCustomerEntity_Id(id);
    }

    @Override
    public List<OrderEntity> getOrdersFromTo(Date from, Date to) {
        Instant start = from.toInstant();
        Instant end = to.toInstant();
        return orderRepository.findAllByOrderTimeBetween(Timestamp.from(start),Timestamp.from(end));
    }
}
