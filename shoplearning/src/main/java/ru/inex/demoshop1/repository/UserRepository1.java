package ru.inex.demoshop1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inex.demoshop1.entity.User1;

public interface UserRepository1 extends JpaRepository<User1, Integer> {
    User1 findByName(String name);
    boolean existsByName(String name);
}
