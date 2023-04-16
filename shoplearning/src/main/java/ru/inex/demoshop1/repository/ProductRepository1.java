package ru.inex.demoshop1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inex.demoshop1.entity.Product1;

@Repository
public interface ProductRepository1 extends JpaRepository<Product1,Integer> {
}
