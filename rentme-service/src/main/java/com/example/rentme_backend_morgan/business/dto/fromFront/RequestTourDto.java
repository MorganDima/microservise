package com.example.rentme_backend_morgan.business.dto.fromFront;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import com.example.rentme_backend_morgan.business.validation.DataCheck;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Valid
public class RequestTourDto {

    AnnoncementDto dto;

    @Size(min = 1, max = 1_000_000)
    long idAnnouncement;

    @Pattern(regexp = "^[A-Za-z0-9]*$")
    String loginRenter;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DataCheck
    LocalDate from;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DataCheck
    LocalDate to;

    @Size(min = 10, max = 170)
    String messageText;
}