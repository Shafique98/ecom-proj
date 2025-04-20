package com.shafeeque.ecom_proj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		return repository.findById(prodID).orElse(null);
	}

	public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());

		return repository.save(product);

	}

	public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		return repository.save(product);

	}

	public void deleteProduct(int id) {
		repository.deleteById(id);
	}

	public List<Product> searchProducts(String keyword) {
		
		return repository.searchProducts(keyword);
	}

}
