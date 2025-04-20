package com.shafeeque.ecom_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shafeeque.ecom_proj.modal.Product;
import com.shafeeque.ecom_proj.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		for (Product p : service.getAllProducts()) {
			System.out.println("ProductController "+p);
		}
		return service.getAllProducts();
	}
}
