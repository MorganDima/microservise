package com.example.rentme_backend_morgan.business.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Viewport {
    @JsonProperty("northeast")
    Northeast northeast;
    @JsonProperty("southwest")
    Southwest southwest;
}
