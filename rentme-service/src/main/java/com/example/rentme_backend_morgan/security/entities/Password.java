package com.example.rentme_backend_morgan.security.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "password")
@NoArgsConstructor
@Getter
@Setter
public class Password {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    Account account;

    String password;

    public Password(Account account,
                    String password) {
        this.account = account;
        this.password = password;
    }
}
