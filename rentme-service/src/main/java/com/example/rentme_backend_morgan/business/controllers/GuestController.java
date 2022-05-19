package com.example.rentme_backend_morgan.business.controllers;


import com.example.rentme_backend_morgan.business.api.BusinessApiEndPoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BusinessApiEndPoints.GUEST)
public class GuestController {


    @GetMapping(BusinessApiEndPoints.GUEST_FIND_PLACE)
    public String findPlace() {
        return "user";
    }

}