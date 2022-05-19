package com.example.rentme_backend_morgan.business.service.google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@ToString
@AllArgsConstructor
public class GoogleDTO {

    String countryName;
    String cityName;
    Integer numberHouse;
    String streetName;
}
