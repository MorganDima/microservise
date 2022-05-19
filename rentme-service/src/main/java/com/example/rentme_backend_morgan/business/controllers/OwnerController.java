package com.example.rentme_backend_morgan.business.controllers;

import com.example.rentme_backend_morgan.business.api.BusinessApiEndPoints;
import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.service.google.MicroServiceGoogle;
import com.example.rentme_backend_morgan.business.service.interfaces.IOwner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.rentme_backend_morgan.business.dto.fromFront.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(BusinessApiEndPoints.OWNER)
@Valid
@CrossOrigin
@Slf4j
public class OwnerController {

    private final IOwner iOwner;
    private final MicroServiceGoogle microServiceGoogle;

    @PostMapping(BusinessApiEndPoints.OWNER_ADD_RENT_OBJECT)
    public ResponseEntity<String> addRentObject(@RequestBody RealtyObjectDto dto) {
        log.info("new realtyObject registration {}", dto);
//        return microServiceGoogle.sendText("DIMa");
        return new ResponseEntity<>(iOwner.addRealityObject(dto), HttpStatus.OK);
    }

    @PostMapping(BusinessApiEndPoints.OWNER_ADD_ANNOUNCEMENT)
    public String addAnnouncement(
            @RequestBody(required = true) AnnoncementDto dto) {
        return iOwner.addAnnouncement(dto);
    }

    @GetMapping(BusinessApiEndPoints.OWNER_GET_PROFILE)
    public OwnerDto getProfile(
            @Pattern(regexp = "^[A-Za-z0-9]*$") String loginOwner) {
        return iOwner.getProfile(loginOwner);
    }

    @PutMapping(BusinessApiEndPoints.OWNER_EDIT_PROFILE)
    public OwnerDto editProfile(
            @RequestBody(required = true) EditProfileDto dto) {
        return iOwner.editProfile(dto);
    }

    @PutMapping(BusinessApiEndPoints.OWNER_EDIT_OBJECT)
    public RealtyObjectDto editObject(
            @RequestBody(required = true) RealtyObjectDto dto) {
        return iOwner.editRealityObject(dto);
    }

    @GetMapping(BusinessApiEndPoints.OWNER_GET_OBJECTS)
    public Set<RealtyObjectDto> getObjects(
            @Pattern(regexp = "^[A-Za-z0-9]*$") String loginOwner) {
        return iOwner.getRealityObjectS(loginOwner);
    }

    @DeleteMapping(BusinessApiEndPoints.OWNER_REMOVE_OBJECT)
    public Set<RealtyObjectDto> removeObject(
            @Pattern(regexp = "^[A-Za-z0-9]*$") String loginOwner,
            int idObject) {
        return iOwner.removeObject(loginOwner, idObject);
    }

    @PostMapping(BusinessApiEndPoints.OWNER_SEND_MESSAGE)
    public String removeObject(
            @RequestBody(required = true) MessageOwnerRenterDto dto) {
        return iOwner.sendMessageToRenter(dto);
    }
}