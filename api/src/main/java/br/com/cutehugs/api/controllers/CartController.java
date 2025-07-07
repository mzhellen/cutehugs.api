package br.com.cutehugs.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cutehugs.api.dtos.CartCreateDTO;
import br.com.cutehugs.api.dtos.CartItemCreateDTO;
import br.com.cutehugs.api.dtos.CartItemResponseDTO;
import br.com.cutehugs.api.dtos.CartItemUpdateDTO;
import br.com.cutehugs.api.dtos.CartResponseDTO;
import br.com.cutehugs.api.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	//create
	@PostMapping
	public ResponseEntity<CartResponseDTO> create(@RequestBody CartCreateDTO cartCreateDTO) {
		CartResponseDTO cartResponseDTO = cartService.create(cartCreateDTO);
		return new ResponseEntity<>(cartResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/show/{cart_id}")
	//show cart
	public ResponseEntity<CartResponseDTO> show(@PathVariable Long cart_id){
		try {
			return new ResponseEntity<>(cartService.show(cart_id), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//insert
	@PostMapping("/insertItem")
	public ResponseEntity<CartItemResponseDTO> insert(@RequestBody CartItemCreateDTO cartItemCreateDTO){
		CartItemResponseDTO cartItemResponseDTO = cartService.insert(cartItemCreateDTO);
		return new ResponseEntity<>(cartItemResponseDTO, HttpStatus.CREATED);
	}
	
	//list items
	@GetMapping("/list/{cart_id}")
	public ResponseEntity<List<CartItemResponseDTO>> list(@PathVariable Long  cart_id){
		return new ResponseEntity<>(cartService.list(cart_id), HttpStatus.OK);
	}
	
	//update or remove items
	@PatchMapping
	public ResponseEntity<CartItemResponseDTO> update(@RequestBody CartItemUpdateDTO cartItemUpdateDTO){
		try{
			return new ResponseEntity<>(cartService.update(cartItemUpdateDTO), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//destroy cart
	@DeleteMapping("/delete/{cart_id}")
	public ResponseEntity<String> destroy(@PathVariable Long cart_id){
		try {
			cartService.delete(cart_id);
			return new ResponseEntity<>("Carrinho deletado com sucesso.", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
