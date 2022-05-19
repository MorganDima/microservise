package com.example.rentme_backend_morgan.business.service.interfaces;


import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.dto.toFront.AnnouncementDtoToFront;
import com.example.rentme_backend_morgan.business.dto.toFront.PartOfAnnDto;

import java.util.Set;

public interface IRenter {

    RenterDto getProfile(String loginRenter);

    RenterDto editProfile(EditProfileDto editRenter);

    Set<PartOfAnnDto> findObjectByPlace(FindPlaceDto findPlace);

   AnnouncementDtoToFront getFullDataByPlace(long idAnnouncement);

    Set<AnnoncementDto> getFavorites(String loginRenter);

    Set<AnnoncementDto> addFavorite(String login, AnnoncementDto dto);

    Set<AnnoncementDto> removeFavorite(String login, long idAnnouncement);

    Set<AnnoncementDto> addHistory(String login, AnnoncementDto dto);

    Set<AnnoncementDto> getHistory(String loginRenter);

    Set<AnnoncementDto> removeHistory(String login, long idAnnouncement);

    String requestTour(RequestTourDto dto);

    OwnerDto applyToOwner(String loginOwner);

    String sendMessageToOwner(MessageOwnerRenterDto contactOwner);//TODO

}