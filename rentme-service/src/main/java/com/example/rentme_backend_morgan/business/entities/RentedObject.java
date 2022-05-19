package com.example.rentme_backend_morgan.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "rentedObject")
public class RentedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne
    RealtyObject realtyObject;

    @ManyToOne
    Renter renter;

    LocalDate rentedFrom;

    public RentedObject(RealtyObject realtyObject,
                        Renter renter,
                        LocalDate rentedFrom) {
        this.realtyObject = realtyObject;
        this.renter = renter;
        this.rentedFrom = rentedFrom;
    }
}