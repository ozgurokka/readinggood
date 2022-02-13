package com.getir.readingGood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingGood.model.dto.BookDTO;
import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ozgurokka on 2/13/22 1:07 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    MockMvc mockMvc;

    @Mock
    private BookController bookController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void persistNewBook() throws  Exception{
        BookDTO bookDTO = BookDTO.builder()
                .name("Suç ve Ceza")
                .stock(20)
                .build();

        String json = mapper.writeValueAsString(bookDTO);
        BookEntity bookEntity = ObjectMapperUtils.map(bookDTO, BookEntity.class);
        Mockito.when(bookController.persistBook(bookDTO)).thenReturn(ResponseEntity.ok(bookEntity));

        mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.stock", Matchers.equalTo(20)));
    }

    @Test
    public void updateStock() throws  Exception{
        BookEntity bookEntity = BookEntity.builder()
                .name("Suç ve Ceza")
                .stock(20)
                .id("1")
                .build();

        String json = mapper.writeValueAsString(bookEntity);

        Mockito.when(bookController.updateBookStock("1",20)).thenReturn(ResponseEntity.ok(bookEntity));

        mockMvc.perform(post("/api/book/1/stock/20")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.stock", Matchers.equalTo(20)));
    }
}
