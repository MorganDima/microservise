package com.example.rentme_backend_morgan.business.dto.fromFront;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class PhotoDto {

    String avatarPhoto;

    List<String> photos;

    public PhotoDto(String avatarPhoto) {
        this.avatarPhoto = avatarPhoto;
    }

    public PhotoDto(String avatarPhoto, List<String> photos) {
        this.avatarPhoto = avatarPhoto;
        this.photos = photos;
    }
}