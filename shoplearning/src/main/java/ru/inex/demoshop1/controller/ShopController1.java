package ru.inex.demoshop1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.inex.demoshop1.dto.UserOrder;
import ru.inex.demoshop1.dto.UserOrderList;
import ru.inex.demoshop1.entity.Category1;
import ru.inex.demoshop1.entity.Order1;
import ru.inex.demoshop1.entity.Product1;
import ru.inex.demoshop1.entity.User1;
import ru.inex.demoshop1.service.ShopService1;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@Controller
//@RequestMapping("")
public class ShopController1 {
    @Autowired
    private ShopService1 shopService1;

    @GetMapping("/")
  //@ResponseBody
    public String getStart() {
        return "getStart";
    }

//----------------Product1----------
    @GetMapping("/product/list")
    public List<Product1> getAllProducts(@RequestParam(name = "sortByName", required = false) String sortByName) {
        return shopService1.getAllProducts(sortByName);
    }

    /*@PostMapping("/product/add")
    public String addProduct(@RequestBody Product1 product1) {
        shopService1.saveProduct(product1);
        return "redirect:/";
    }*/

    @PostMapping("/product/add")
    public Product1 addProduct(@RequestBody Product1 product1) {
        shopService1.saveProduct(product1);
        return product1;
    }

    @PostMapping("/product/update")
    public Product1 updateProduct(@RequestBody Product1 product1) {
       shopService1.updateProduct(product1.getId(), product1.getCount());
                return product1;
    }

    @PostMapping("/product/buy")
    //public Order1 buyProduct(@RequestBody Product1 product1, Integer amount, User1 user1) {
    public List<Order1> buyProduct(@RequestBody UserOrderList userOrders) {
        return userOrders.getOrderList().stream().map(userOrder -> shopService1.buyProduct(userOrders.getName(),userOrder)).collect(Collectors.toList());
        //return shopService1.buyProduct(product1, amount, user1);
    }

    @PostMapping("/product/buyid/{id}/{count}")
    public Product1 buyProductByIdPV(@PathVariable Integer id, @PathVariable Integer count) {
            Product1 product1 = shopService1.updateProduct(id,count);
            return product1;
        }

    @PostMapping("/product/buyid")
        public Product1 buyProductByIdRP(@RequestParam(name = "id") Integer id, @RequestParam(name = "count") Integer count) {
        Product1 product1 = shopService1.updateProduct(id,count);
        return product1;
    }

    @PostMapping("/product/rate")
    public Product1 addRatingProduct(@RequestParam(name = "productId") Integer productId, @RequestParam(name = "userId") Integer userId, @RequestParam(name = "orderId") Integer orderId, @RequestParam(name = "mark") Integer mark) {
        Product1 product1 = shopService1.addRatingProduct(productId, userId, orderId, mark);
        return product1;
    }
//----------------Category1----------
@GetMapping("/catalog/list")
//public List<Category1> getAllCategories(@RequestParam(name = "my_param", required = false) String my_param)
public List<Category1> getAllCategories() {
    //System.out.println(my_param);
    return shopService1.getAllProductsCategories();   }

    //----------------User1----------
@GetMapping("/user/list")
public List<User1> getAllUsers() {
    return shopService1.getAllUsers();}

@PostMapping("/user/add")
public User1 addUser(@RequestBody User1 user1) {
    shopService1.addUser(user1);
    return user1;
}

//
}