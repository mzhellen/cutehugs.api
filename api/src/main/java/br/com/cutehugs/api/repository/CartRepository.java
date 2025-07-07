package br.com.cutehugs.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import br.com.cutehugs.api.entities.Cart;
import br.com.cutehugs.api.entities.CartItem;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	@NativeQuery("SELECT cart_items.* FROM cart_items WHERE cart_id = :id")
	public List<CartItem> listCartItems(Long id);
}
