package com.example.rentme_backend_morgan.business.dto.fromFront;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Valid
public class MessageOwnerRenterDto {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    String loginRenter;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    String loginOwner;

    @Size(min = 10, max = 170) //TODO if we are want to send our message via sms
    String messageText;

}