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
public class Northeast {
    @JsonProperty("lat")
    Double lat;
    @JsonProperty("lng")
    Double lng;
}
