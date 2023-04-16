package ru.inex.demoshop1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.inex.demoshop1.dto.UserOrder;
import ru.inex.demoshop1.entity.*;
import ru.inex.demoshop1.repository.*;

import java.util.List;
import java.util.Optional;

@Service
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ShopService1 {

    //@Autowired
    private final ProductRepository1 productRepository1;
    private final CategoryRepository1 categoryRepository1;
    private final UserRepository1 userRepository1;
    private final OrderRepository1 orderRepositoty1;
    private final RatingRepository1 ratingRepository1;

    @Autowired
    public ShopService1(ProductRepository1 productRepository1, CategoryRepository1 categoryRepository1, UserRepository1 userRepository1, OrderRepository1 orderRepositoty1, RatingRepository1 ratingRepository1) {
        this.productRepository1 = productRepository1;
        this.categoryRepository1 = categoryRepository1;
        this.userRepository1 = userRepository1;
        this.orderRepositoty1 = orderRepositoty1;
        this.ratingRepository1 = ratingRepository1;
    }

    public List<Product1> getAllProducts(String sortByName) {
        List<Product1> result = productRepository1.findAll();
        if (sortByName != null) {
            switch (sortByName) {
                case "asc":
                    result = productRepository1.findAll(Sort.by("name").ascending());
                    break;
                case "desc":
                    result = productRepository1.findAll(Sort.by("name").descending());
                    break;
            }
        }
        return result;
    }

    public List<Category1> getAllProductsCategories() {
        return categoryRepository1.findAll();
    }

    public Product1 saveProduct(Product1 product1) {
        return productRepository1.save(product1);
    }

    public Product1 updateProduct(Integer id, Integer count) {
        Product1 product1Updated = null;
        Optional<Product1> optional = productRepository1.findById(id);
        if (optional.isPresent()) {
            product1Updated = optional.get();
        }

        if (product1Updated != null) {
            product1Updated.setCount(count);
        }
        productRepository1.save(product1Updated);
        return product1Updated;
    }

    public List<User1> getAllUsers() {
        return userRepository1.findAll();
    }

    public User1 addUser(User1 user1) {
        return userRepository1.save(user1);
    }

    public Order1 addOrder(Order1 order1, User1 user1) {
        return orderRepositoty1.save(order1);
    }

    //
    public Order1 buyProduct(String name, UserOrder userOrder) {
        Product1 product1 = productRepository1.getReferenceById(userOrder.getProduct1Id());
        if (product1.getCount() >= userOrder.getAmount()) {
            User1 user1;
            if (StringUtils.hasText(name)) {
                if (!userRepository1.existsByName(name)) {
                    user1 = addUser(new User1(name));
                } else {
                    user1 = userRepository1.findByName(name);
                }
                Order1 order1 = new Order1(user1.getId(), product1.getId(), userOrder.getAmount());
                product1.setCount(product1.getCount() - order1.getAmount());
                updateProduct(product1.getId(), product1.getCount());
                orderRepositoty1.save(order1);
                return order1;
            }
            throw new RuntimeException("User has no login(name is empty)");
        }
        throw new RuntimeException("No enough count");
    }

    public Product1 addRatingProduct(Integer productId, Integer userId, Integer orderId, Integer mark) {
        //System.out.println("productId 0 " + productId);
        Order1 order1 = orderRepositoty1.getReferenceById(orderId);
        Product1 product1 = productRepository1.getReferenceById(productId);
        if (orderRepositoty1.existsById(orderId)
                && order1.getUser1Id() == userId
                && order1.getProduct1Id() == productId) {
            Rating1 rating1 = new Rating1(productId, userId, orderId, mark);
            //System.out.println("productId " + productId);
            ratingRepository1.save(rating1);
            List<Rating1> rating1s = ratingRepository1.findAllByProductId(productId);
            System.out.println(rating1);
            System.out.println(rating1s);
            Double sumMark = 0.0;
            Integer count = 0;
            Double avgRating = 0.0;
            for (Rating1 rating11 : rating1s) {
                if (rating11.getMark() != null) {
                    sumMark += rating11.getMark();
                    count++;
                }
                if (count >= 3) {
                    avgRating = (sumMark / count);
                }
                System.out.println("sumMark " + sumMark);
                System.out.println("count " + count);
                System.out.println("avgRating " + avgRating);
                product1.setRating(avgRating);
                product1 = productRepository1.save(product1);
            }
        } else {
            throw new RuntimeException("No user order with product to rate");
        }
        return product1;
    }

//
}
