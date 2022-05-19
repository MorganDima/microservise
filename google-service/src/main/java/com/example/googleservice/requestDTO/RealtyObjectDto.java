package com.example.googleservice.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public class RealtyObjectDto {

    String countryName;
    String cityName;
    String streetName;
    int numberHouse;
}