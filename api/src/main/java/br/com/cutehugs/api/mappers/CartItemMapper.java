package br.com.cutehugs.api.mappers;

import java.math.BigDecimal;

import br.com.cutehugs.api.dtos.CartItemCreateDTO;
import br.com.cutehugs.api.dtos.CartItemResponseDTO;
import br.com.cutehugs.api.entities.Cart;
import br.com.cutehugs.api.entities.CartItem;
import br.com.cutehugs.api.entities.Product;

public class CartItemMapper {
	public static CartItemResponseDTO toDTO(CartItem cartItem) {
		return new CartItemResponseDTO(cartItem.getId(), cartItem.getQuantity(), cartItem.getSubTotal(), cartItem.getCart().getId(), ProductMapper.toDTO(cartItem.getProduct()));
	}
	
	public static CartItem toEntity(CartItemCreateDTO cartItemCreateDTO, Cart cart, Product product) {
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(cartItemCreateDTO.quantity());
		cartItem.setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(cartItemCreateDTO.quantity())));
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		return cartItem;
	}
}
