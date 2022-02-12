package com.getir.readingGood.model.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by ozgurokka on 2/12/22 6:31 PM
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    private String month;
    private int bookCount;
    private int orderCount;
    private BigDecimal totalAmount;
}
