package com.example.rentme_backend_morgan.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentme_backend_morgan.business.entities.Announcement;
import java.time.LocalDate;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {

    boolean existsByAvailable(LocalDate available);

    Announcement findAnnouncementById(long idAnnouncement);
}
