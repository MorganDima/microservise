package com.example.rentme_backend_morgan.business.controllers;

import com.example.rentme_backend_morgan.business.api.BusinessApiEndPoints;
import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.dto.toFront.AnnouncementDtoToFront;
import com.example.rentme_backend_morgan.business.dto.toFront.PartOfAnnDto;
import com.example.rentme_backend_morgan.business.service.interfaces.IRenter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Set;


@RestController
@RequestMapping(BusinessApiEndPoints.USER)
@RequiredArgsConstructor
public class RenterController {

    private final IRenter iRenter;

    @GetMapping(BusinessApiEndPoints.USER_GET_PROFILE)
    public RenterDto getProfile(
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginRenter) {
        return iRenter.getProfile(loginRenter);
    }

    @PostMapping(BusinessApiEndPoints.USER_EDIT_PROFILE)
    public RenterDto editProfile(
            @RequestBody(required = true) @Valid EditProfileDto dto) {
        return iRenter.editProfile(dto);
    }

    @GetMapping(BusinessApiEndPoints.USER_FIND_PLACE)
    public Set<PartOfAnnDto> findPlace(
            @RequestBody(required = true) @Valid FindPlaceDto dto) {
        return iRenter.findObjectByPlace(dto);
    }

    @GetMapping(BusinessApiEndPoints.USER_GET_FULL_DATA_BY_PLACE)
    public AnnouncementDtoToFront getFullDataByPlace(
            @Valid @Min(1) @Max(1_000_000) long idAnnouncement) {
        return iRenter.getFullDataByPlace(idAnnouncement);
    }

    //TODO return string that object is added
    @PostMapping(BusinessApiEndPoints.USER_ADD_FAVORITES)
    public Set<AnnoncementDto> addFavorites(
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginRenter,
            @Valid AnnoncementDto dto) {
        return iRenter.addFavorite(loginRenter, dto);
    }

    @GetMapping(BusinessApiEndPoints.USER_GET_FAVORITES)
    public Set<AnnoncementDto> getFavorites(
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginRenter) {
        return iRenter.getFavorites(loginRenter);
    }

    @DeleteMapping(BusinessApiEndPoints.USER_REMOVE_FAVORITES)
    public Set<AnnoncementDto> removeFavorites(
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginRenter,
            @Valid @Min(1) @Max(1_000_000) long idAnnouncement) {
        return iRenter.removeFavorite(loginRenter, idAnnouncement);
    }

    //TODO valid not need, because we not put id from front
    @PostMapping(BusinessApiEndPoints.USER_ADD_HISTORY)
    public Set<AnnoncementDto> addHistory(
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginRenter,
            @Valid AnnoncementDto dto) {
        return iRenter.addHistory(loginRenter, dto);
    }

    @GetMapping(BusinessApiEndPoints.USER_GET_HISTORY)
    public Set<AnnoncementDto> getHistory(
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginRenter) {
        return iRenter.getHistory(loginRenter);
    }

    @DeleteMapping(BusinessApiEndPoints.USER_REMOVE_HISTORY)
    public Set<AnnoncementDto> removeHistory(
            @Valid @Min(1) @Max(1_000_000) long idAnnouncement,
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginRenter) {
        return iRenter.removeHistory(loginRenter, idAnnouncement);
    }

    @PostMapping(BusinessApiEndPoints.USER_REQUEST_TOUR)
    public String requestTour(
            @RequestBody(required = true)
            @Valid RequestTourDto dto) {
        return iRenter.requestTour(dto);
    }

    @PostMapping(BusinessApiEndPoints.USER_APPLY_OWNER)
    public OwnerDto applyOwner(
            @Valid @Pattern(regexp = "^[A-Za-z0-9]*$") String loginOwner) {
        return iRenter.applyToOwner(loginOwner);
    }

    @PostMapping(BusinessApiEndPoints.USER_MESSAGE_TO_OWNER)
    public String contactOwner(
            @RequestBody(required = true) @Valid MessageOwnerRenterDto dto) {
        return iRenter.sendMessageToOwner(dto);
    }
}