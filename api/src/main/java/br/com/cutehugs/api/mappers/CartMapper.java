package br.com.cutehugs.api.mappers;

import br.com.cutehugs.api.dtos.CartResponseDTO;
import br.com.cutehugs.api.entities.Cart;
import br.com.cutehugs.api.entities.User;

public class CartMapper {
	
	public static CartResponseDTO toDTO(Cart cart) {
		return new CartResponseDTO(cart.getId(), cart.getUser().getId());
	}
	
	public static Cart toEntity(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		return cart;
	}

}
