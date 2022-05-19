package com.example.rentme_backend_morgan.business.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Geometry {

    @JsonProperty("location")
    public Location location;

    @JsonProperty("viewport")
    public Viewport viewport;
}
