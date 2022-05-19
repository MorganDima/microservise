package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.rentme_backend_morgan.business.entities.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

}

