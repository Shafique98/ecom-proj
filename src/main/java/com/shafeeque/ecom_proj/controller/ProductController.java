package com.shafeeque.ecom_proj.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shafeeque.ecom_proj.modal.Product;
import com.shafeeque.ecom_proj.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/product/{prodID}")
	public ResponseEntity<Product> getProductByID(@PathVariable int prodID) {

		Product product = service.getProductByID(prodID);

		if (null != product) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/product")
	public ResponseEntity<?> addProuct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
		try {
			Product product1 = service.addProduct(product, imageFile);
			return new ResponseEntity<>(product1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product/{productId}/image")
	public ResponseEntity<byte[]> getImagebyProductID(@PathVariable int productId) {
		Product product = service.getProductByID(productId);
		byte[] imageFile = product.getImageData();

		return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProuct(@PathVariable int id, @RequestPart Product product,
			@RequestPart MultipartFile imageFile) {

		Product product1 = null;
		try {
			product1 = service.updateProduct(id, product, imageFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
		}
		if (null != product1) {
			return new ResponseEntity<>("Updated", HttpStatus.OK);
		} else {
			System.err.println("not updated");
			return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		Product product = service.getProductByID(id);
		if (null != product) {
			service.deleteProduct(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(String keyword){
		System.out.println("Searching '"+keyword+"'");
		List<Product> products = service.searchProducts(keyword);
		return new ResponseEntity<>( products, HttpStatus.OK);
	}
}