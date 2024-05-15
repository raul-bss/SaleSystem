package com.example.SaleSys.Repositories;

import com.example.SaleSys.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

/* 
  
   não precisa por a anotação @Repository 
   pois já herda da interface JpaRepository
 
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
