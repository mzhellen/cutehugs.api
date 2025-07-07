 package br.com.cutehugs.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cutehugs.api.dtos.ProductCreateDTO;
import br.com.cutehugs.api.dtos.ProductResponseDTO;
import br.com.cutehugs.api.dtos.ProductUpdateDTO;
import br.com.cutehugs.api.entities.Product;
import br.com.cutehugs.api.mappers.ProductMapper;
import br.com.cutehugs.api.repository.ProductRepository;
import jakarta.transaction.Transactional;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductResponseDTO create(ProductCreateDTO productCreateDTO) {
		Product product = ProductMapper.toEntity(productCreateDTO); 
		Product productResponse = productRepository.save(product);
		return ProductMapper.toDTO(productResponse);
	}
	
	public List<ProductResponseDTO> list(){
		return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
	}
	
	public ProductResponseDTO show(long id){
		Product product = productRepository.findById(id).orElseThrow(
							() -> new RuntimeException("Produto com o id " +id+ " não encontrado")
		);
		return ProductMapper.toDTO(product);
	}
	
	@Transactional
	public ProductResponseDTO update(ProductUpdateDTO productUpdateDTO) {
		Product product = productRepository.findById(productUpdateDTO.id())
											.orElseThrow( () -> new RuntimeException("Produto não encontrado para alteração"));
		
		if(productUpdateDTO.name() != "" || productUpdateDTO.name() != null) {
			product.setName(productUpdateDTO.name());
		}
		if(productUpdateDTO.price() != null) {
			product.setPrice(productUpdateDTO.price());
		}
		if(productUpdateDTO.description() != "" || productUpdateDTO.description() != null) {
			product.setDescription(productUpdateDTO.description());
		}
		if(productUpdateDTO.stock() != -1) {
			product.setStock(productUpdateDTO.stock());
		}
		if(productUpdateDTO.imageURL() != "" || productUpdateDTO.imageURL() != null){
			product.setImageURL(productUpdateDTO.imageURL());
		}
		
		return ProductMapper.toDTO(productRepository.save(product));
		
	}
	
	@Transactional
	public void destroy(long id) {
		Product product = productRepository.findById(id).orElseThrow( () -> new RuntimeException("Produto não encontrado para deleção"));
		productRepository.delete(product);
	}
}
