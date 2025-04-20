package com.shafeeque.ecom_proj.modal;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	private int id;
	private String name;
	private String description;
	private String brand;
	private BigDecimal prize;
	private String category;
	private Date releaseDate;
	private boolean avaliable;
	private int quantity;

}
