package br.com.cutehugs.api.dtos;

import java.math.BigDecimal;

public record CartItemResponseDTO(
		Long id,
		int quantity,
		BigDecimal subTotal,
		Long cart_id,
		ProductResponseDTO product) {

}
