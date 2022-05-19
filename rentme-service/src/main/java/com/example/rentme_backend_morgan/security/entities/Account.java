package com.example.rentme_backend_morgan.security.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account")
    String accId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    List<Password> passwords;

    @Column(name = "revoked")
    boolean revoked = false;

    @Column(name = "role")
    String role;

    public Account(String accId, String role) {
        this.accId = accId;
        this.role = role;
    }
}