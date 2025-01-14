package com.example.SaleSys.Config;

import com.example.SaleSys.Entites.*;
import com.example.SaleSys.Entites.Enums.OrderStatus;
import com.example.SaleSys.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")

public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Pizza");
        Category cat2 = new Category(null, "Hamburguer");
        Category cat3 = new Category(null, "Sushi");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));


        Product p1 = new Product(null, "Dr Meat", "Full of meat hamburguer.", 10.5, "");
        Product p2 = new Product(null, "Double Cheese", "Two types of cheeses pizza.", 20.0, "");
        Product p3 = new Product(null, "Hot Roll", "10 Hot Sushi.", 10.0, "");
        Product p4 = new Product(null, "Uramaki", "10 Uramakis.", 12.90, "");

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

        //adciona cada produto a sua categoria
        p1.getCategory().add(cat2);
        p2.getCategory().add(cat1);
        p3.getCategory().add(cat3);
        p4.getCategory().add(cat3);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));


        User u1 = new User(null, "Nedved Junior", "nedved@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Pirlo Silva", "pirlo@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.DELIVERED, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.SHIPPED, u1);


        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p4, 2, p4.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);
        orderRepository.save(o1);

    }

}
