package com.example.rentme_backend_morgan.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
@Entity
@Table(name = "renter")
public class Renter {

    @Id
    @Column(length = 100, name = "login")
    String login;

    @Column(length = 50, name = "first_name")
    String firstName;

    @Column(length = 50, name = "last_name")
    String lastName;

    @Column(length = 13, name = "phone_number")
    String numberTelephone;

    @Column(name = "is_revoked", updatable = true)
    boolean isRevoked;

//    @Email
    String email;

    @Column(name = "about_me")
    String aboutMe;

    //@Column(name = "photo_renter", columnDefinition = "MEDIUMTEXT")
    @Column(name = "photo_renter")
    String photoRenter;

    @ManyToMany(mappedBy = "renterH")
    Set<Announcement> announcementH = new HashSet<>();

    @ManyToMany(mappedBy = "renterF")
    Set<Announcement> announcementF = new HashSet<>();

    @OneToMany(mappedBy = "renter")
    Set<Message> messages = new HashSet<>();

    public Renter(String login,
                  String firstName,
                  String lastName,
                  String numberTelephone,
                  String email,
                  String aboutMe,
                  String photoRenter) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberTelephone = numberTelephone;
        this.aboutMe = aboutMe;
        this.email = email;
        this.photoRenter = photoRenter;
    }

    public Renter(String login) {
        this.login = login;
    }
}