package com.runner.web.repo;

import com.runner.web.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles, Long> {
    Optional <Roles> findByName(String name);
}
