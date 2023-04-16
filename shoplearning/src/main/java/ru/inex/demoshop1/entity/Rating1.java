package ru.inex.demoshop1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer productId;
    private Integer userId;
    private Integer orderId;
    private Integer mark;

    public Rating1(Integer productId, Integer userId, Integer orderId, Integer mark) {
        this.productId = productId;
        this.userId = userId;
        this.orderId = orderId;
        this.mark = mark;
}

}
