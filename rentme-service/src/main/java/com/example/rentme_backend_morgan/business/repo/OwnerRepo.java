package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.rentme_backend_morgan.business.entities.Owner;

@Repository
public interface OwnerRepo extends CrudRepository<Owner, String> {

    Owner findOwnerByLogin(String loginOwner);

    @Transactional
    @Modifying
    @Query(value = "UPDATE owner SET is_revoked = :is_rev WHERE login = :log", nativeQuery = true)
    void revokedByLogin(@Param("is_rev") boolean flag,
                        @Param("log") String log);

}

