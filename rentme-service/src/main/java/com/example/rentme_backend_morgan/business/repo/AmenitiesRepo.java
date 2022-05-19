package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentme_backend_morgan.business.entities.Amenitie;

public interface AmenitiesRepo extends JpaRepository<Amenitie, String> {

    Amenitie findAmenitieByName(String elem);
}

