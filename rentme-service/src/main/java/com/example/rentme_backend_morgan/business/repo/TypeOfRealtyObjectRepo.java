package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentme_backend_morgan.business.entities.TypeOfRealtyObject;

public interface TypeOfRealtyObjectRepo extends JpaRepository<TypeOfRealtyObject, String> {


    TypeOfRealtyObject findTypeOfRealtyObjectByName(String name);
}


