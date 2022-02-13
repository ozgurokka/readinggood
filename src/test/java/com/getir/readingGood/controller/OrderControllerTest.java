package com.getir.readingGood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.dto.OrderDTO;
import com.getir.readingGood.model.dto.OrderDetailDTO;
import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderDetailEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import com.getir.readingGood.util.ObjectMapperUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ozgurokka on 2/13/22 7:13 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    MockMvc mockMvc;

    @Mock
    private OrderController orderController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSaveOrder() throws  Exception{

        List<OrderDetailDTO> orderDetailEntityLis = new ArrayList<>();
        orderDetailEntityLis.add(OrderDetailDTO.builder().bookId("1").count(10).build());


        OrderDTO orderDTO = OrderDTO.builder()
                .amount(new BigDecimal(10))
                .orderDetailEntityList(orderDetailEntityLis)
                .customerId("1")
                .build();


        String json = mapper.writeValueAsString(orderDTO);
        OrderEntity orderEntity = ObjectMapperUtils.map(orderDTO,OrderEntity.class);
        Mockito.when(orderController.placeOrder(orderDTO)).thenReturn(ResponseEntity.ok(orderEntity));

        mockMvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }
}
