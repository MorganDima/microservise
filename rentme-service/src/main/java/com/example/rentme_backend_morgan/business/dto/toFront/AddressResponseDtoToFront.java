package com.example.rentme_backend_morgan.business.dto.toFront;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDtoToFront {

    @NonNull
    String fullAddress;
    @NonNull
    String nameLocation;
    @NonNull
    long locationLat;
    @NonNull
    long locationLng;

}
