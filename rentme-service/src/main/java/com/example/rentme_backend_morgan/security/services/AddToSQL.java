package com.example.rentme_backend_morgan.security.services;

import com.example.rentme_backend_morgan.business.repo.OwnerRepo;
import com.example.rentme_backend_morgan.business.repo.RenterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.rentme_backend_morgan.business.entities.Owner;
import com.example.rentme_backend_morgan.business.entities.Renter;
import com.example.rentme_backend_morgan.security.dto.RegisterDto;

@Service
@RequiredArgsConstructor
public class AddToSQL {

    private final RenterRepo renterRepo; //TODO put new renter for for create personal place
    private final OwnerRepo ownerRepo; //TODO put new renter for for create personal place

    @Transactional
    public void addProfileToSQL(RegisterDto dto) {

        switch (dto.getRole()) {
            case "USER" -> renterRepo.save(new Renter(
                    dto.getLogin(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getNumberTelephone(),
                    dto.getEmail(),
                    dto.getAboutMe(),
                    dto.getAvatarPhoto()));

            case "OWNER" -> ownerRepo.save(new Owner(
                    dto.getLogin(),
                    dto.getEmail(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getNumberTelephone(),
                    dto.getAboutMe(),
                    dto.getAvatarPhoto()));
        }
    }
}
