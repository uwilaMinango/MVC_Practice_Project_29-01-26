package com.runner.web.repo;


import com.runner.web.models.Club;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepo extends JpaRepository<Club, Long> {
    Optional findByTitle(String title);
}
