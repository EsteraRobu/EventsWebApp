package com.main.erobu.data.repository;

import com.main.erobu.data.entry.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findById(Integer id);

    Optional<Roles> findRolesByRole(String role);
}

