package com.main.erobu.data.repository;
import com.main.erobu.data.entry.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
