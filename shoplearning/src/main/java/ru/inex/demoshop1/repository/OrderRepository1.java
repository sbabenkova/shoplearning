package ru.inex.demoshop1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inex.demoshop1.entity.Order1;

@Repository
public interface OrderRepository1 extends JpaRepository<Order1,Integer> {

}
