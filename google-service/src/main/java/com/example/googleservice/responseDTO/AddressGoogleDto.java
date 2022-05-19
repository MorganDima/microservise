package com.example.googleservice.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressGoogleDto {

    public Candidates[] candidates;
    public String status;
}

