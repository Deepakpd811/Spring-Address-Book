package com.bridgelab.addressBook.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    @NotEmpty(message = "First name is required")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$", message = "First name must start with a capital letter and be at least 3 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$", message = "Last name must start with a capital letter and be at least 3 characters")
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    @Email(message = "Invalid email format")
    private String email;

}
