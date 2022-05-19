package com.example.googleservice.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
