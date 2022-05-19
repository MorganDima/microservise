package com.example.rentme_backend_morgan.business.dto.toFront;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class PartOfAnnDto
{
    double lat;
    double lng;
    String nameOfRentObject;
    int price;
    int size;
    int bedrooms;
//    Set<Amenitie> amenities = new HashSet<>();
    String avatarPhoto;
}