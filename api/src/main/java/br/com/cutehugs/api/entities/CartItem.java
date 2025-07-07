package br.com.cutehugs.api.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	private BigDecimal subTotal;
	// Conexão com o Cart
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	// Conexão com o Product
	@ManyToOne
	@JoinColumn(name = "product_id")
	Product product;
	
	
	public CartItem() {}
	
	public CartItem(Long id, int quantity, BigDecimal subTotal, Cart cart) {
		this.id = id;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.cart = cart;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
