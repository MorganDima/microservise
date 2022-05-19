package com.example.rentme_backend_morgan.business.service.logica;

import com.example.rentme_backend_morgan.business.service.interfaces.IGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rentme_backend_morgan.business.dto.fromFront.FindPlaceDto;
import com.example.rentme_backend_morgan.business.dto.toFront.AnnouncementDtoToFront;
import com.example.rentme_backend_morgan.business.repo.AnnouncementRepo;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class GuestLogica implements IGuest {

    @Autowired
    AnnouncementRepo announcementRepo;

    @Override
    public List<AnnouncementDtoToFront> findPlace(FindPlaceDto findPlace) {
        return null;
    }

    @Override
    public String writeToAdmin(String massage) {
        return null;
    }
}