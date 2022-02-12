package com.getir.readingGood.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by ozgurokka on 2/12/22 2:12 PM
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDTO {
    @NotBlank(message = "Name can not be empty !")
    private String name;
    @NotBlank(message = "Sur Name can not be empty !")
    private String surname;
    @NotBlank(message = "E-Mail can not be empty")
    @Email(message = "E-mail format Not Valid !")
    private String email;
}
