package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentme_backend_morgan.business.entities.Photo;

public interface PhotoRepo extends JpaRepository<Photo, String> {

}

