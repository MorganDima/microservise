package com.example.rentme_backend_morgan.business.service.logica;

import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.entities.*;
import com.example.rentme_backend_morgan.business.repo.*;
import com.example.rentme_backend_morgan.business.service.interfaces.IOwner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.example.rentme_backend_morgan.business.bussinesChecks.BussinesChecks;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.Candidates;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.Location;
import com.example.rentme_backend_morgan.business.mapper.BusinessMapper;
import com.example.rentme_backend_morgan.business.service.google.MicroServiceGoogle;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@EnableTransactionManagement
public class OwnerLogica implements IOwner {


    private final RenterRepo renterRepo;
    private final OwnerRepo ownerRepo;
    private final AnnouncementRepo announcementRepo;
    private final MessageRepo messageRepo;
    private final RealtyObjectRepo realtyObjectRepo;
    private final AddressRepo addressRepo;
    private final TypeOfRealtyObjectRepo typeOfRealtyObjectRepo;
    private final PhotoRepo photoRepo;
    private final AmenitiesRepo amenitiesRepo;
    private final MicroServiceGoogle microServiceGoogle;


    @Override
    public OwnerDto getProfile(String loginOwner) {
        BussinesChecks.ifLoginIsLogin(loginOwner);
        Owner owner = ownerRepo.findOwnerByLogin(loginOwner);
        new GuestLogica().printAge();
        return BusinessMapper.ownerToDto(owner);
    }

    @Override
    public OwnerDto editProfile(EditProfileDto dto) {
        BussinesChecks.ifLoginIsLogin(dto.getLogin());

        Owner owner = ownerRepo.findOwnerByLogin(dto.getLogin());

        owner.setFirstName(dto.getFirstName());
        owner.setLastName(dto.getLastName());
        owner.setNumberTelephone(dto.getNumberTelephone());
        owner.setEmail(dto.getEmail());
        owner.setAboutMe(dto.getAboutMe());
        owner.setPhotoOwner(dto.getPhoto());
        ownerRepo.save(owner);

        return BusinessMapper.ownerToDto(owner);
    }

    @Override
    @Transactional
            (transactionManager = "businessTransactionManager")
    public synchronized String addRealityObject(RealtyObjectDto dto) {

        BussinesChecks.ifLoginIsLogin(dto.getLoginOwner());

        Address address = getAddress(dto); //TODO in this part i use google service

        BussinesChecks.checkIsRealtyObjectExists(dto.getApptNumber(), address.getId());

        Owner owner = ownerRepo.findOwnerByLogin(dto.getLoginOwner());

        TypeOfRealtyObject typeOfRealtyObject =
                getTypeOfRealtyObject(dto.getTypeOfRealtyObject());

        RealtyObject realtyObject = new RealtyObject(
                address,
                owner,
                dto.getNameRentObject(),
                dto.getApptNumber(),
                dto.getSize(),
                dto.getFloor(),
                dto.getRooms(),
                dto.getAvatarPhoto(),
                typeOfRealtyObject
        );

        saveAmenities(dto.getAmenities());
        savePhotos(realtyObject, dto.getPhotos());
        saveObjectAmenit(realtyObject, dto.getAmenities());

        return "Realty object is added to DB";
    }

    public void saveObjectAmenit(RealtyObject realtyObject, Set<String> amenities) {

        amenities.forEach(elem -> {
            realtyObject.getAmenitiess().add(amenitiesRepo.findAmenitieByName(elem));
        });

    }

    public void saveAmenities(Set<String> amenities) {
        amenities.forEach(elem -> {
            amenitiesRepo.save(new Amenitie(elem));
        });
    }

    @Transactional
            (transactionManager = "businessTransactionManager")
    public TypeOfRealtyObject getTypeOfRealtyObject(String name) {

        TypeOfRealtyObject typeOfRealtyObject =
//                typeOfRealtyObjectRepo.getById(name);
                typeOfRealtyObjectRepo.findTypeOfRealtyObjectByName(name);

        if (typeOfRealtyObject == null) {
            typeOfRealtyObject = new TypeOfRealtyObject(name);
        }

        return typeOfRealtyObject;
    }

    @Transactional
            (transactionManager = "businessTransactionManager")
    public Address getAddress(RealtyObjectDto dto) {


        //Candidates candidates = createCandidates(dto);
        Candidates candidates = microServiceGoogle.createCandidates(
//                new GoogleDTO(
//                        dto.getCountryName(),
//                        dto.getCityName(),
//                        dto.getNumberOfHouse(),
//                        dto.getStreetName()));

                dto.getCountryName(),
                dto.getCityName(),
                dto.getStreetName(),
                dto.getNumberOfHouse());


        String fullAddress = candidates.getFormatted_address();

        Location location = candidates.getGeometry().getLocation();

        Address address = BussinesChecks.checkIsAddressExist(fullAddress, dto.getBlockNumber());

        if (address == null) {
            address = addressRepo.save(new Address(
                    fullAddress,
                    dto.getCountryName(),
                    dto.getCityName(),
                    dto.getStreetName(),
                    dto.getBlockNumber(),
                    dto.getNumberOfHouse(),
                    location.getLat(),
                    location.getLng()));
        }

        return address;
    }

    @Transactional
            (transactionManager = "businessTransactionManager")
    public void savePhotos(RealtyObject realtyObject, List<String> photos) {
        photos.forEach(ph ->
        {
            photoRepo.save(new Photo(realtyObject, ph));
        });
    }

    @Override
    public String addAnnouncement(AnnoncementDto dto) {
        BussinesChecks.ifLoginIsLogin(dto.getLoginOwner());

        RealtyObject realtyObject = BussinesChecks.checkIsAnnouncementExist(dto);
        announcementRepo.save(
                new Announcement(
                        realtyObject,
                        dto.getAvailable(),
                        dto.getPrice(),
                        dto.getMinDurationOfStay(),
                        dto.getSecurityDeposit(),
                        dto.getCancellationTime()));

        return "Announcement is added to DB";
    }

    @Override
    public RealtyObjectDto editRealityObject(RealtyObjectDto dto) {
        BussinesChecks.ifLoginIsLogin(dto.getLoginOwner());
        return null;
    }

    @Override
    public Set<RealtyObjectDto> getRealityObjectS(String loginOwner) {
        BussinesChecks.ifLoginIsLogin(loginOwner);
        return null;
    }

    @Override
    public Set<RealtyObjectDto> removeObject(String loginOwner, int idObject) {
        BussinesChecks.ifLoginIsLogin(loginOwner);
        return null;
    }

    @Override
    public synchronized String sendMessageToRenter(MessageOwnerRenterDto dto) {
        BussinesChecks.ifLoginIsLogin(dto.getLoginOwner());

        Renter renter = renterRepo.findRenterByLogin(dto.getLoginRenter());
        Owner owner = ownerRepo.findOwnerByLogin(dto.getLoginOwner());

        messageRepo.save(new Message(
                renter,
                owner,
                "OWNER",
                "RENTER",
                dto.getMessageText()));

        return "Some text";
    }
}