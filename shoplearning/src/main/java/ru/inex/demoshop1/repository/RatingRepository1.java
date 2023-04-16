package ru.inex.demoshop1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inex.demoshop1.entity.Rating1;

import java.util.List;

public interface RatingRepository1 extends JpaRepository<Rating1, Integer> {

    List<Rating1> findAllByProductId(Integer productId);
}
