package com.shafeeque.ecom_proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shafeeque.ecom_proj.modal.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
