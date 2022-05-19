package com.example.rentme_backend_morgan.business.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(of = {"fullAddressId"})
@Entity
@Table(name = "address")
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_address", length = 249)
    String fullAddress;

    @Column(name = "country_name")
    String country;

    @Column(name = "city_name", length = 100)
    String city;

    @Column(name = "street_name", length = 100)
    String streetName;

    @Column(name = "block_number")
    String blockNumber;//TODO -1

    @Column(name = "number_house")
    int numberHouse;

    double lat;
    double lng;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    List<RealtyObject> realtyObjects = new ArrayList<>();

    public Address(String fullAddress,
                   String country,
                   String city,
                   String streetName,
                   String blockNumber,
                   int numberHouse,
                   double lat,
                   double lng) {
        this.fullAddress = fullAddress;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.blockNumber = blockNumber == null ? "" : blockNumber;
        this.numberHouse = numberHouse;
        this.lat = lat;
        this.lng = lng;
    }
}