package com.crio.coderHack.exchanges;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "UserId cant be null, empty or blank")
    private String UserId;

    @NotNull(message = "Score cant be null")
    @Range(min = 1, max = 100, message = "Score can only be in range 1 to 100 inclusive.")
    private Integer score;
}
