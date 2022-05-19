package com.example.rentme_backend_morgan.business.service.interfaces;


import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import java.util.Set;
public interface IOwner {

    String addRealityObject(RealtyObjectDto dto) ;

    String addAnnouncement(AnnoncementDto dto);

    OwnerDto getProfile(String loginRenter);

    OwnerDto editProfile(EditProfileDto dto);

    RealtyObjectDto editRealityObject(RealtyObjectDto dto);

    Set<RealtyObjectDto> getRealityObjectS(String loginOwner);

    Set<RealtyObjectDto> removeObject(String loginOwner, int idObject);
    
    String sendMessageToRenter(MessageOwnerRenterDto dto);

}