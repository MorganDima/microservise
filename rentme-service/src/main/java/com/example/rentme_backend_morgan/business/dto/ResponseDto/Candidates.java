package com.example.rentme_backend_morgan.business.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Candidates {

    @JsonProperty("formatted_address")
    private String formatted_address;

    @JsonProperty("geometry")
    private Geometry geometry;

    @JsonProperty("name")
    private String name;

    @JsonProperty("types")
    private String[] types;
}
