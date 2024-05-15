package com.example.SaleSys.Repositories;

import com.example.SaleSys.Entites.OrderItem;
import com.example.SaleSys.Entites.PK.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
