package com.getir.readingGood.model.dto;

import com.getir.readingGood.model.entity.BookEntity;
import lombok.*;

/**
 * Created by ozgurokka on 2/12/22 11:15 PM
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private String bookId;
    private int count;
}
