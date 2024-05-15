package com.example.SaleSys.Service;

import com.example.SaleSys.Entites.Category;
import com.example.SaleSys.Entites.Order;
import com.example.SaleSys.Entites.User;
import com.example.SaleSys.Repositories.OrderRepository;
import com.example.SaleSys.Service.Exceptions.DatabaseException;
import com.example.SaleSys.Service.Exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found " + id));
    }

    public Order insert(Order obj) {
        return repository.save(obj);
    }

    public void deleteUser(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Order updateUser(Long id, Order obj) {
        try {
            Order entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }


    }

    public void updateData(Order entity, Order obj) {
        entity.setMoment(obj.getMoment());
        entity.setClient(obj.getClient());
        entity.setPayment(obj.getPayment());


    }


}
