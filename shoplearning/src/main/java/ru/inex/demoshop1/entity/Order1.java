package ru.inex.demoshop1.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Order1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer Id;
    private Integer user1Id;
    private Integer product1Id;
    private Integer amount;

    public Order1() {
    }

    public Order1(Integer user1Id, Integer product1Id, Integer amount) {
        this.user1Id = user1Id;
        this.product1Id = product1Id;
        this.amount = amount;
    }
}

