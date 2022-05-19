package com.example.googleservice.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GoogleDTO {

    String countryName;
    String cityName;
    String streetName;
    Integer numberHouse;

}
