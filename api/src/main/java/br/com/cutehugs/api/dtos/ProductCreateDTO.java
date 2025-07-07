package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

public record ProductCreateDTO(
		String name,
		BigDecimal price,
		String description,
		int stock,
		String imageURL) {

}