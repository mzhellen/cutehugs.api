package br.com.cutehugs.api.mappers;

import br.com.cutehugs.api.dtos.ProductCreateDTO;
import br.com.cutehugs.api.dtos.ProductResponseDTO;
import br.com.cutehugs.api.entities.Product;


public class ProductMapper {
	
	public static ProductResponseDTO toDTO(Product product) {
	return new ProductResponseDTO(product.getId(),product.getName(),product.getPrice(), product.getDescription(),product.getStock(),product.getImageURL());
    }
	
	public static Product toEntity(ProductCreateDTO productCreateDTO) {
		Product product = new Product();
		product.setName(productCreateDTO.name());
		product.setPrice(productCreateDTO.price());
		product.setDescription(productCreateDTO.description());
		product.setStock(productCreateDTO.stock());
		product.setImageURL(productCreateDTO.imageURL());
		return product;
	}
	
}
