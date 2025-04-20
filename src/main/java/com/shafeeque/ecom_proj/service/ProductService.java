package com.shafeeque.ecom_proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shafeeque.ecom_proj.modal.Product;
import com.shafeeque.ecom_proj.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> getAllProducts() {
		
		return repository.findAll();
	}

	public Product getProductByID(int prodID) {
		return repository.findById(prodID).get();
	}

}
