package br.com.cutehugs.api.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cutehugs.api.dtos.CartCreateDTO;
import br.com.cutehugs.api.dtos.CartItemCreateDTO;
import br.com.cutehugs.api.dtos.CartItemResponseDTO;
import br.com.cutehugs.api.dtos.CartItemUpdateDTO;
import br.com.cutehugs.api.dtos.CartResponseDTO;
import br.com.cutehugs.api.entities.Cart;
import br.com.cutehugs.api.entities.CartItem;
import br.com.cutehugs.api.entities.Product;
import br.com.cutehugs.api.entities.User;
import br.com.cutehugs.api.mappers.CartItemMapper;
import br.com.cutehugs.api.mappers.CartMapper;
import br.com.cutehugs.api.repository.CartItemRepository;
import br.com.cutehugs.api.repository.CartRepository;
import br.com.cutehugs.api.repository.ProductRepository;
import br.com.cutehugs.api.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	public CartResponseDTO create(CartCreateDTO cartCreateDTO) {
		User user = userRepository.findById(cartCreateDTO.user_id()).orElseThrow(
				() -> new RuntimeException("Usuário com o id (" + cartCreateDTO.user_id() + ") não encontrado para criação de carrinho")
		);
		Cart cart = CartMapper.toEntity(user);
		CartResponseDTO cartResponseDTO = CartMapper.toDTO(cartRepository.save(cart));
		return cartResponseDTO;
	}
	
	public CartResponseDTO show(Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Carrinho não encontrado!")
		);
		return CartMapper.toDTO(cart);
	}
	
	public CartItemResponseDTO insert(CartItemCreateDTO cartItemCreateDTO) {
		Cart cart = cartRepository.findById(cartItemCreateDTO.cart_id()).orElseThrow(
				() -> new RuntimeException("Carrinho não encontrado!")
		);
		Product product = productRepository.findById(cartItemCreateDTO.product_id()).orElseThrow(
				() -> new RuntimeException("Produto não encontrado!")
		);
		CartItem cartItem = findProduct(product.getId(), cart.getId());
		if(cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity() + cartItemCreateDTO.quantity());
			cartItem.setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(cartItemCreateDTO.quantity())));
		}else {
			cartItem = CartItemMapper.toEntity(cartItemCreateDTO, cart, product);
		}
		if(product.getStock() < cartItem.getQuantity()) {
			throw new RuntimeException("Quantidade inválida");
		}
		CartItemResponseDTO cartItemResponseDTO = CartItemMapper.toDTO(cartItemRepository.save(cartItem));
		return cartItemResponseDTO;
	}
	
	public List<CartItemResponseDTO> list(Long cart_id){
		return cartRepository.listCartItems(cart_id).stream().map(CartItemMapper::toDTO).toList();
	}
	
	@Transactional
	public CartItemResponseDTO update(CartItemUpdateDTO cartItemUpdateDTO) {
		CartItem cartItem = cartItemRepository.findById(cartItemUpdateDTO.id()).orElseThrow(
				() -> new RuntimeException("Item não encontrado")
		);
		if(cartItemUpdateDTO.quantity() == 1) {
			if(cartItem.getProduct().getStock() < cartItem.getQuantity() + 1) {
				throw new RuntimeException("Quantidade inválida");
			}else {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setSubTotal(cartItem.getSubTotal().add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(1))));
			}
		}else if(cartItemUpdateDTO.quantity() != 1){
			cartItem.setQuantity(cartItem.getQuantity() - 1);
			cartItem.setSubTotal(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
			if(cartItem.getQuantity() == 0) {
				cartItemRepository.delete(cartItem);
				return null;
			}
		}
		return CartItemMapper.toDTO(cartItemRepository.save(cartItem));
	}
	
	@Transactional
	public void delete(Long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Carrinho não encontrado para deleção")
		);
		cartRepository.delete(cart);
	}	
	  
	private CartItem findProduct(Long product_id, Long cart_id) {
		List<CartItem> items = cartRepository.listCartItems(cart_id);
		for (CartItem cartItem : items) {
			if(product_id == cartItem.getProduct().getId()) {
				return cartItem;
			}
		}
		return null;
	}

}
