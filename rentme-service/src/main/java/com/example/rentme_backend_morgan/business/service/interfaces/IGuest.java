package com.example.rentme_backend_morgan.business.service.interfaces;

import com.example.rentme_backend_morgan.business.dto.fromFront.FindPlaceDto;
import com.example.rentme_backend_morgan.business.dto.toFront.AnnouncementDtoToFront;

import java.util.List;

public interface IGuest {

    List<AnnouncementDtoToFront> findPlace(FindPlaceDto findPlace);

    String writeToAdmin(String massage);

    default void printAge() {
        System.out.println("Age 25");
    }

}
