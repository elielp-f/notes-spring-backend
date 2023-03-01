package com.eliel.inotes.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data @Builder
public class RegisterDTO {
    @NotBlank(message="First name is required.")
    private String firstName;
    @NotBlank(message="Last name is required.")
    private String lastName;
    @NotBlank(message = "E-mail is required.")
    @Email
    private String email;
    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password should be contain 8 characters with one letter and number.")
    private String password;
}
