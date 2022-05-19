package com.example.rentme_backend_morgan.business.dto.fromFront;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
public class FindPlaceDto
{

    @Pattern(regexp = "^[A-Za-z0-9]*$")
    String login;

    @Pattern(regexp = "^[A-Za-z]*$")
    String country;

    @Pattern(regexp = "^[A-Za-z]*$")
    String city;

    @JsonInclude(Include.NON_NULL)
    @Min(1)
    @Max(1_000_000)
    int minPrice;

    @JsonInclude(Include.NON_NULL)
    @Min(1)
    @Max(1_000_000)
    int maxPrice;

    @JsonInclude(Include.NON_NULL)
//    @Min(1)
//    @Max(10)
    Integer[] rooms;

}