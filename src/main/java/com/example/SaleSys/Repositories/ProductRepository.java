package com.example.SaleSys.Repositories;

import com.example.SaleSys.Entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
