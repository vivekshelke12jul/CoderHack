package com.crio.coderHack.exchanges;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "UserId cant be null, empty or blank")
    private String userId;

    @NotBlank(message = "username cant be null, empty or blank")
    private String username;
}
