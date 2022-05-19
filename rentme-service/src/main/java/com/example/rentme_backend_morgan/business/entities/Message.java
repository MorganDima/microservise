package com.example.rentme_backend_morgan.business.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime localDateTimeMessage;

    @Column(name = "date_from")
    LocalDate dateFrom;

    @Column(name = "date_to")
    LocalDate dateTo;

    @Column(name = "message_text")
    String messageText;

    @Column(name = "message_from")
    String messageFrom;

    @Column(name = "message_to")
    String messageTo;

    @ManyToOne
    Owner owner;

    @ManyToOne
    Renter renter;

    @ManyToOne
    Announcement announcement;

    public Message(
            Renter renter,
            Owner owner,
            String messageFrom,
            String messageTo,
            LocalDate dateFrom,
            LocalDate dateTo,
            Announcement announcement,
            String messageText) {
        this.renter = renter;
        this.owner = owner;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.localDateTimeMessage = LocalDateTime.now();
        this.announcement = announcement;
        this.messageText = messageText;
    }

    public Message(
            Renter renter,
            Owner owner,
            String messageFrom,
            String messageTo,
            String messageText) {
        this.renter = renter;
        this.owner = owner;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.localDateTimeMessage = LocalDateTime.now();
        this.messageText = messageText;
    }
}