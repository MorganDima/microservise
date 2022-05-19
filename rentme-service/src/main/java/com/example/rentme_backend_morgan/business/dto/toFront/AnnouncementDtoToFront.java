package com.example.rentme_backend_morgan.business.dto.toFront;

import lombok.Getter;
import lombok.Setter;
import com.example.rentme_backend_morgan.business.entities.Photo;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class AnnouncementDtoToFront {

    Set<Photo> photos = new HashSet<>();
    String ownerFullName;
    int minDurationOfStay;
    int size;
    int securityDeposit;
    int floor;
    int bedrooms;
    int cancellationTime;
    LocalDate available;
    int price;
    String description;
    double lat;
    double lng;
}