package com.example.rentme_backend_morgan.business.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "announcement")
@ToString
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    int price;
    LocalDate available;
    int minDurationOfStay;
    int securityDeposit;
    int cancellationTime;

    @ManyToOne
    RealtyObject realtyObject;

    @ManyToMany
    @JoinTable(name = "history")
    List<Renter> renterH = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "favorite")
    Set<Renter> renterF = new HashSet<>();

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    Set<Message> message;

    public Announcement(RealtyObject realtyObject,
                        LocalDate available,
                        int price,
                        int minDurationOfStay,
                        int securityDeposit,
                        int cancellationTime) {
        this.realtyObject = realtyObject;
        this.price = price;
        this.available = available;
        this.minDurationOfStay = minDurationOfStay;
        this.securityDeposit = securityDeposit;
        this.cancellationTime = cancellationTime;
    }
}