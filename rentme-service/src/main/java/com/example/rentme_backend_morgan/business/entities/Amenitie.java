package com.example.rentme_backend_morgan.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(exclude = "realtyObjects")`

@Entity(name = "amenities")
public class Amenitie {

    @Id
    @Column(length = 100)
    String name;

    @ManyToMany(mappedBy = "amenitiess")
    @JsonIgnore
    Set<RealtyObject> realtyObjectss = new HashSet<>();

    public Amenitie(String name) {
        super();
        this.name = name;
    }


}
