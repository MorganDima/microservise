package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.rentme_backend_morgan.business.entities.Renter;

public interface RenterRepo extends JpaRepository<Renter, String> {

    Renter findRenterByLogin(String loginRenter);

    @Transactional
    @Modifying
    @Query(value = "UPDATE renter SET is_revoked = :is_rev WHERE login = :log", nativeQuery = true)
    void revokedByLogin(@Param("is_rev") boolean flag,
                       @Param("log") String log);
}

