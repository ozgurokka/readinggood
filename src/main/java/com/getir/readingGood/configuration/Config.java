package com.getir.readingGood.configuration;

import com.getir.readingGood.model.dto.OrderDTO;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ozgurokka on 2/12/22 1:38 PM
 */
@Configuration
@EnableSwagger2
@EnableScheduling
public class Config {

}
