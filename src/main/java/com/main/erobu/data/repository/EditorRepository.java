package com.main.erobu.data.repository;

import com.main.erobu.data.entry.Editor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Integer> {

    Editor findByUsername(String username);

    List<Editor> findByActive(Integer active);

    <S extends Editor> Optional<S> findById(Example<Editor> example);

}
