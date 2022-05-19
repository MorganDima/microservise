package com.example.rentme_backend_morgan.business.mapper;

import org.modelmapper.ModelMapper;
import com.example.rentme_backend_morgan.business.dto.fromFront.AnnoncementDto;
import com.example.rentme_backend_morgan.business.dto.fromFront.OwnerDto;
import com.example.rentme_backend_morgan.business.dto.fromFront.RenterDto;
import com.example.rentme_backend_morgan.business.dto.toFront.AnnouncementDtoToFront;
import com.example.rentme_backend_morgan.business.dto.toFront.PartOfAnnDto;
import com.example.rentme_backend_morgan.business.entities.Announcement;
import com.example.rentme_backend_morgan.business.entities.Owner;
import com.example.rentme_backend_morgan.business.entities.Renter;

public class BusinessMapper {

    static ModelMapper modelMapper = new ModelMapper();

    public static RenterDto renterToDto(Renter renter) {
        return modelMapper.map(renter, RenterDto.class);
    }

    public static OwnerDto ownerToDto(Owner owner) {
        return modelMapper.map(owner, OwnerDto.class);
    }

    public static Announcement announcementToEntity(AnnoncementDto dto) {
        return modelMapper.map(dto, Announcement.class);
    }

    public static AnnoncementDto announcementToDto(Announcement announcement) {
        return modelMapper.map(announcement, AnnoncementDto.class);
    }

    public static AnnouncementDtoToFront fullInfoAboutObject(Announcement announcement) {
        return new AnnouncementDtoToFront();
    }

    public static PartOfAnnDto announcementToPartDto(Announcement announcement) {
        return new PartOfAnnDto(
                announcement.getRealtyObject().getAddress().getLat(),
                announcement.getRealtyObject().getAddress().getLng(),
                announcement.getRealtyObject().getNameOfRentObject(),
                announcement.getPrice(),
                announcement.getRealtyObject().getSize(),
                announcement.getRealtyObject().getBedrooms(),
                announcement.getRealtyObject().getAvatarPhoto());
    }
}