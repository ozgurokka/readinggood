package com.getir.readingGood.model.dto;

import com.getir.readingGood.model.entity.OrderDetailEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by ozgurokka on 2/12/22 11:01 PM
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private BigDecimal amount;
    private String customerId;
    private List<OrderDetailDTO> orderDetailEntityList;
}
