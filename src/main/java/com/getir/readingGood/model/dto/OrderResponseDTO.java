package com.getir.readingGood.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ozgurokka on 2/11/22 10:33 PM
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private BigDecimal amount;
    private String customerId;
    private List<OrderDetailDTO> orderDetailEntityList;
    private Timestamp orderTime;
}
