package com.example.SaleSys.Repositories;

import com.example.SaleSys.Entites.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
