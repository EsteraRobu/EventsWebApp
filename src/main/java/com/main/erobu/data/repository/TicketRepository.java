package com.main.erobu.data.repository;

import com.main.erobu.data.entry.Editor;
import com.main.erobu.data.entry.Event;
import com.main.erobu.data.entry.Ticket;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    List<Ticket> findByEvent(Event event);

    <S extends Ticket> Optional<S> findById(Example<Ticket> example);
}
