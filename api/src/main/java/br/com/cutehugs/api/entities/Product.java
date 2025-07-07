package br.com.cutehugs.api.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // define essa classe como entidade
@Table(name="products")

public class Product {
	@Id  // define o id como chave primária 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // usado pra gerar um ID sequêncial 
	private Long id;
	private String name;
	private BigDecimal price;
	private String description;
	private int stock;
	private String imageURL;

	
	public Product() {}	
	
	// Construtor: método de inicialização do objeto Products
	public Product(Long id, String name, BigDecimal price, String description, int stock, String imageURL) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.imageURL = imageURL;
	}
	
	// Gets e Sets para permitir o acesso aos atributos aqui declarados 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	
}
