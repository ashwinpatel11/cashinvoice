package com.user.dto;

import jakarta.validation.constraints.*;

public record UserRequestDto(
        long id,
        @NotNull(message = "name should not null")
        @NotBlank(message = "name can not be blank")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 20 characters")
        String name,
        @NotNull(message = "name should not null")
        @NotBlank(message = "name can not be blank")
        @Email
        @Size(min = 5, max = 50, message = "email must be between 5 and 20 characters")
        String email,

        @NotNull(message = "name should not null")
        @NotBlank(message = "name can not be blank")
        @Size(min = 3, max = 50, message = "Name must be between 5 and 20 characters")
        String password,

        @NotNull(message = "name should not null")
        @NotBlank(message = "name can not be blank")
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Mobile number must be a valid 10-digit Indian number"
        )
        String mobile
)
{

}

