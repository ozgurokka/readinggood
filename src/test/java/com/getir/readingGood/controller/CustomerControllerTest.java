package com.getir.readingGood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.dto.OrderResponseDTO;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderDetailEntity;
import com.getir.readingGood.util.ObjectMapperUtils;
import com.getir.readingGood.util.OrderState;
import com.getir.readingGood.util.ReadingGoodUtils;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ozgurokka on 2/13/22 11:46 AM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    MockMvc mockMvc;

    @Mock
    private CustomerController customerController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testUserShouldBeRegistered() throws  Exception{

        CustomerDTO customerDTO = CustomerDTO.builder()
                .email("1@1.com")
                .name("ozgur")
                .surname("okka")
                .build();


        String json = mapper.writeValueAsString(customerDTO);
        CustomerEntity customerEntity = ObjectMapperUtils.map(customerDTO,CustomerEntity.class);
        Mockito.when(customerController.register(customerDTO)).thenReturn(ResponseEntity.ok(customerEntity));

        mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.surname", Matchers.equalTo("okka")))
                .andExpect(jsonPath("$.name", Matchers.equalTo("ozgur")));

    }

}
