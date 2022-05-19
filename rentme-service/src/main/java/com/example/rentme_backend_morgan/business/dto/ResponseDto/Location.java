package com.example.rentme_backend_morgan.business.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {
    @JsonProperty("lat")
    Double lat;
    @JsonProperty("lng")
    Double lng;
}
