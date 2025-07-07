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

import br.com.cutehugs.api.dtos.ProductCreateDTO;
import br.com.cutehugs.api.dtos.ProductResponseDTO;
import br.com.cutehugs.api.dtos.ProductUpdateDTO;
import br.com.cutehugs.api.services.ProductService;

@RestController
@RequestMapping("/api/products") // definição do  caminho
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductCreateDTO productCreateDTO){
		ProductResponseDTO productResponseDTO = productService.create(productCreateDTO);
		return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> list(){
		return new ResponseEntity<>(productService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{product_id}")
	public ResponseEntity<Object> show(@PathVariable Long product_id){
		try {
			return new ResponseEntity<>(productService.show(product_id), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<Object> update(@RequestBody ProductUpdateDTO productUpdateDTO){
		try {
			return new ResponseEntity<>(productService.update(productUpdateDTO), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{product_id}")
	public ResponseEntity<String> destroy(@PathVariable long product_id){
		try {
			productService.destroy(product_id);
			return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
}
