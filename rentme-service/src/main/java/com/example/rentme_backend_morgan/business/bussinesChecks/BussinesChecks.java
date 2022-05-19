package com.example.rentme_backend_morgan.business.bussinesChecks;

import com.example.rentme_backend_morgan.business.api.BusinessResponses;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.AddressGoogleDto;
import com.example.rentme_backend_morgan.business.dto.fromFront.AnnoncementDto;
import com.example.rentme_backend_morgan.business.repo.AddressRepo;
import com.example.rentme_backend_morgan.business.repo.RealtyObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.rentme_backend_morgan.business.entities.Address;
import com.example.rentme_backend_morgan.business.entities.RealtyObject;

import javax.annotation.PostConstruct;

@Component
@Transactional
public class BussinesChecks {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    RealtyObjectRepo realtyObjectRepo;

    private static AddressRepo addressRepoY;
    private static RealtyObjectRepo realtyObjectRepoO;

    @PostConstruct
    private void plugAutowired() {
        addressRepoY = addressRepo;
        realtyObjectRepoO = realtyObjectRepo;
    }

    public static void ifLoginIsLogin(String login) {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(login))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, DTO_IS_NULL);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public static void checkIsRealtyObjectExists(String apptNumber, Long id) {

        String nameObject = realtyObjectRepoO.existsByInfo(apptNumber, id);

        if (nameObject != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BusinessResponses.REALTY_OBJECT_ALREADY_EXISTS);
        }
    }

    public static void checkAddress(ResponseEntity<AddressGoogleDto> response) {
        if (!response.getBody().status.equals("OK"))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, BusinessResponses.EMPTY_ADDRESS);
    }

    public static Address checkIsAddressExist(String fullAddress, String blockNumber) {

        Address address = addressRepoY.findAddressByFullAddressAndBlockNumber(fullAddress, blockNumber);

        return address;
    }

    public static RealtyObject checkIsAnnouncementExist(AnnoncementDto dto) {

        RealtyObject realtyObject = realtyObjectRepoO.findRealtyObject(dto.getIdObject());
        realtyObject.getAnnouncement().forEach(ann ->
        {
            if (ann.getAvailable().equals(dto.getAvailable())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BusinessResponses.EMPTY_ADDRESS);
            }
        });
        return realtyObject;
    }
}