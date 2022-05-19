package com.example.rentme_backend_morgan.business.dto.fromFront;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import com.example.rentme_backend_morgan.business.validation.DataCheck;
import javax.validation.Valid;
import java.time.LocalDate;

@Getter
@Setter
@Valid
public class AnnoncementDto {

    String loginOwner;
    long idObject;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DataCheck(maxYear = 12)
    LocalDate available;

    int price;
    int minDurationOfStay;
    int securityDeposit;
    int cancellationTime;
}
