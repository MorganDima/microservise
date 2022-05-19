package com.example.rentme_backend_morgan.business.entities;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @Column(name = "login", length = 100)
    String login;

    @Email
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "is_revoked", updatable = true)
    boolean isRevoked;

    @Column(name = "number_telephone")
    String numberTelephone;

    @Column(name = "about_me")
    String aboutMe;

    //    @Column(name = "photo_owner", columnDefinition = "MEDIUMTEXT")
    @Column(name = "photo_owner")
    String photoOwner;

    @OneToMany(mappedBy = "owner")
    List<RealtyObject> realtyObjects = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    Set<Message> message = new HashSet<>();

    public Owner(String login,
                 String email,
                 String firstName,
                 String lastName,
                 String numberTelephone,
                 String aboutMe,
                 String photoOwner) {
        this.login = login;
        this.aboutMe = aboutMe;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberTelephone = numberTelephone;
        this.email = email;
        this.photoOwner = photoOwner;
    }
}