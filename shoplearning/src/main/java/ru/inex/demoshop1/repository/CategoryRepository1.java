package ru.inex.demoshop1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inex.demoshop1.entity.Category1;

@Repository
public interface CategoryRepository1 extends JpaRepository<Category1, Integer> {
}
