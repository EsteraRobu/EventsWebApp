package com.main.erobu.data.repository;
import com.main.erobu.data.entry.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByUsername(String username);



}
