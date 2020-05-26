package com.main.erobu.data.repository;

import com.main.erobu.data.entry.Editor;
import com.main.erobu.data.entry.Event;
import com.main.erobu.data.entry.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByName(String name);

    List<Event> findByDateStartAfter(Timestamp currentDate);

    List<Event> findByEditor(Editor editor);

    Optional<Event> findById(Integer id);

    List<Event> findByEventCategory(EventCategory eventCategory);


}
