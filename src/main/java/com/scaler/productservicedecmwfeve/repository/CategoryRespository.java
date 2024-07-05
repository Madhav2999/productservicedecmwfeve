package com.scaler.productservicedecmwfeve.repository;

import com.scaler.productservicedecmwfeve.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Long>
{
    Optional<Category> findByName(String name);

    Optional<Category>findById(long id);

    List<Category>findAll();
}
