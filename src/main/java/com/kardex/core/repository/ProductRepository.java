package com.kardex.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.core.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {

}
