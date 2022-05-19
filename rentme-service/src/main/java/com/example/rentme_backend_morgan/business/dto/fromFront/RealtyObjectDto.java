package com.example.rentme_backend_morgan.business.dto.fromFront;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Lob;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Valid
@ToString
public class RealtyObjectDto implements Serializable {

    @Size(min = 5, max = 50)
    String loginOwner;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "[A-Za-z]+")
    String countryName;

    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-z]+")
    String cityName;

    @Size(min = 3, max = 50)
    @Pattern(regexp = "[A-Za-z'`]+")
    String streetName;

    @NotNull
    @Min(1) @Max(500)
    int numberOfHouse;

    @JsonInclude(Include.NON_NULL)
    @NotNull
    @Pattern(regexp = "[A-Za-z]+")
    String nameRentObject;

    @JsonInclude(Include.NON_NULL)
    @NotNull @Min(1) @Max(1500)
    int size;

    @JsonInclude(Include.NON_NULL)
    @NotNull @Min(1) @Max(300)
    int floor;

    @JsonInclude(Include.NON_NULL)
    @NotNull @Min(1) @Max(50)
    int rooms;

    @JsonInclude(Include.NON_NULL)
    @Pattern(regexp = "[A-Za-z0-9]+")
    @Size(min = 1, max = 500)
    String apptNumber;

    @JsonInclude(Include.NON_NULL)
    @Pattern(regexp = "[A-Za-z0-9]+")
    @Size(min = 1, max = 500)
    String blockNumber;

    @JsonInclude(Include.NON_NULL)
    @Size(max = 50_000)
    String avatarPhoto;

    @JsonInclude(Include.NON_NULL)
    List<String> photos;

    @JsonInclude(Include.NON_NULL)
    String typeOfRealtyObject;

    @JsonInclude(Include.NON_NULL)
    Set<String> amenities;

}