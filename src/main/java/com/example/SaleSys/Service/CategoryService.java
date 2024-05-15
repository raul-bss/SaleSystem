package com.example.SaleSys.Service;

import com.example.SaleSys.Entites.Category;
import com.example.SaleSys.Entites.User;
import com.example.SaleSys.Repositories.CategoryRepository;
import com.example.SaleSys.Service.Exceptions.DatabaseException;
import com.example.SaleSys.Service.Exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found " + id));
    }

    public Category insert(Category obj) {
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

    public Category updateUser(Long id, Category obj) {
        try {
            Category entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }


    }

    public void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }
}
