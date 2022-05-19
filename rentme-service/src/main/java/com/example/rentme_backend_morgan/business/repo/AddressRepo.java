package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentme_backend_morgan.business.entities.Address;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Long> {


    Address findAddressByFullAddressAndBlockNumber(String fullAddress, String blockNumber);

    List<Address> findAddressByCountryAndCity(String country, String city);

}

