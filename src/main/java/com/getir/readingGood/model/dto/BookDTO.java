package com.getir.readingGood.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * Created by ozgurokka on 2/12/22 7:13 PM
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    @NotBlank(message = "Book name can not be empty !")
    private String name;

    @PositiveOrZero(message = "Stock number can not be negatif !")
    private int stock;
}
